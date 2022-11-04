package com.cloud.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号国际化校验
 *
 * @author zl
 * @date 2022/11/1 16:09
 */
public class LibPhoneNumberUtil {
    private static final PhoneNumberUtil PHONE_UTIL = PhoneNumberUtil.getInstance();

    /**
     * 判断手机号是否有效
     *
     * @param phoneNumber 手机号
     * @return boolean
     */
    public static boolean checkPhoneNumber(String phoneNumber) {
        // 解析号码
        Phonenumber.PhoneNumber swissNumberProto = getPhoneNumber(phoneNumber, null);
        // 校验号码
        return PHONE_UTIL.isValidNumber(swissNumberProto);
    }

    /**
     * 根据国家代码和手机号  判断手机号是否有效
     *
     * @param countryCode 国际电话区号 86-大陆，886-台湾，852-香港，853-澳门
     *                    81-日本，82-韩国，84-越南，850-朝鲜
     * @param phoneNumber 手机号
     * @return boolean
     */
    public static boolean checkPhoneNumber(String countryCode, String phoneNumber) {
        int code = Integer.parseInt(countryCode);
        long phone = Long.parseLong(phoneNumber);
        Phonenumber.PhoneNumber pn = new Phonenumber.PhoneNumber();
        pn.setCountryCode(code);
        pn.setNationalNumber(phone);
        return PHONE_UTIL.isValidNumber(pn);
    }

    /**
     * 手机号格式化
     *
     * @param phoneNumber       手机号
     * @param phoneNumberFormat 格式化类型：INTERNATIONAL, NATIONAL, E164
     *                          INTERNATIONAL 国际标准, 输出 "+41 44 668 18 00"
     *                          NATIONAL 国内标准, 输出 "044 668 18 00"
     *                          E164 E164标准, 输出 "+41446681800"
     * @return String
     */
    public static String formatPhoneNumber(String phoneNumber, PhoneNumberUtil.PhoneNumberFormat phoneNumberFormat) {
        // 解析号码
        Phonenumber.PhoneNumber swissNumberProto = getPhoneNumber(phoneNumber, null);
        // 格式化
        return PHONE_UTIL.format(swissNumberProto, phoneNumberFormat);
    }

    /**
     * 格式化成从其他国家打过来的格式
     *
     * @param phoneNumber       手机号
     * @param regionCallingFrom 格式化类型：US, CH
     * @return String
     */
    public static String getRegionCallingFrom(String phoneNumber, String regionCallingFrom) {
        // 解析号码
        Phonenumber.PhoneNumber swissNumberProto = getPhoneNumber(phoneNumber, null);
        // 格式化成从其他国家打过来的格式
        return PHONE_UTIL.formatOutOfCountryCallingNumber(swissNumberProto, regionCallingFrom);
    }

    private static Phonenumber.PhoneNumber getPhoneNumber(String phoneNumber, String defaultRegion) {
        if (null == defaultRegion) {
            defaultRegion = "CH";
        }
        // 解析号码
        Phonenumber.PhoneNumber swissNumberProto = null;
        try {
            swissNumberProto = PHONE_UTIL.parse(phoneNumber, defaultRegion);
        } catch (NumberParseException e) {
            System.err.println("号码解析异常: " + e.toString());
        }
        // 校验号码
        return swissNumberProto;
    }

    public static void main(String[] args) {
        // 1、校验的号码
        String swissNumberStr = "044 668 18 00";
        // 校验号码
        boolean isVaild = checkPhoneNumber(swissNumberStr);
        // return true
        System.out.println(swissNumberStr + ", 号码校验结果：" + isVaild);

        // 2、校验带国际电话区号
        String countryCode = "886";
        // 校验的号码
        String phoneNumber = "18770913918";
        // 校验号码
        boolean isVaild1 = checkPhoneNumber(countryCode, phoneNumber);
        // return true
        System.out.println(countryCode + "," + phoneNumber + ", 国际电话区号-号码校验结果：" + isVaild1);


        // 3、格式化的号码
        String swissNumberStr1 = "044 668 18 00";
        // 国际标准, 输出 "+41 44 668 18 00"
        String international = formatPhoneNumber(swissNumberStr1, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
        System.out.println(swissNumberStr1 + ", 国际标准格式化：" + international);

        // 国内标准, 输出 "044 668 18 00"
        String national = formatPhoneNumber(swissNumberStr1, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
        System.out.println(swissNumberStr1 + ", 国内标准格式化：" + national);

        // E164标准, 输出 "+41446681800"
        String e164 = formatPhoneNumber(swissNumberStr1, PhoneNumberUtil.PhoneNumberFormat.E164);
        System.out.println(swissNumberStr1 + ", E164标准格式化：" + e164);

        // 4、格式化的号码
        String swissNumberStr2 = "044 668 18 00";
        // 格式化成从美国打过来的格式，输出 "011 41 44 668 18 00"
        String callingFrom = getRegionCallingFrom(swissNumberStr2, "US");
        System.out.println(swissNumberStr2 + ", 格式化成从美国打过来的格式：" + callingFrom);
    }

    public enum MobileRegularExp {
        /*以下是项目可能设计到的市场*/
        CN("中国", "^(\\+?0?86\\-?)?1[345789]\\d{9}$"),
        TW("台湾", "^(\\+?886\\-?|0)?9\\d{8}$"),
        HK("香港", "^(\\+?852\\-?)?[569]\\d{3}\\-?\\d{4}$"),
        MS("马来西亚", "^(\\+?6?01){1}(([145]{1}(\\-|\\s)?\\d{7,8})|([236789]{1}(\\s|\\-)?\\d{7}))$"),
        PH("菲律宾", "^(\\+?0?63\\-?)?\\d{10}$"),
        TH("泰国", "^(\\+?0?66\\-?)?\\d{10}$"),
        SG("新加坡", "^(\\+?0?65\\-?)?\\d{10}$"),
        /*以下是其他国家的手机号校验正则*/
        DZ("阿尔及利亚", "^(\\+?213|0)(5|6|7)\\d{8}$"),
        SY("叙利亚", "^(!?(\\+?963)|0)?9\\d{8}$"),
        SA("沙特阿拉伯", "^(!?(\\+?966)|0)?5\\d{8}$"),
        US("美国", "^(\\+?1)?[2-9]\\d{2}[2-9](?!11)\\d{6}$"),
        CZ("捷克共和国", "^(\\+?420)? ?[1-9][0-9]{2} ?[0-9]{3} ?[0-9]{3}$"),
        DE("德国", "^(\\+?49[ \\.\\-])?([\\(]{1}[0-9]{1,6}[\\)])?([0-9 \\.\\-\\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$"),
        DK("丹麦", "^(\\+?45)?(\\d{8})$"),
        GR("希腊", "^(\\+?30)?(69\\d{8})$"),
        AU("澳大利亚", "^(\\+?61|0)4\\d{8}$"),
        GB("英国", "^(\\+?44|0)7\\d{9}$"),
        CA("加拿大", "^(\\+?1)?[2-9]\\d{2}[2-9](?!11)\\d{6}$"),
        IN("印度", "^(\\+?91|0)?[789]\\d{9}$"),
        NZ("新西兰", "^(\\+?64|0)2\\d{7,9}$"),
        ZA("南非", "^(\\+?27|0)\\d{9}$"),
        ZM("赞比亚", "^(\\+?26)?09[567]\\d{7}$"),
        ES("西班牙", "^(\\+?34)?(6\\d{1}|7[1234])\\d{7}$"),
        FI("芬兰", "^(\\+?358|0)\\s?(4(0|1|2|4|5)?|50)\\s?(\\d\\s?){4,8}\\d$"),
        FR("法国", "^(\\+?33|0)[67]\\d{8}$"),
        IL("以色列", "^(\\+972|0)([23489]|5[0248]|77)[1-9]\\d{6}"),
        HU("匈牙利", "^(\\+?36)(20|30|70)\\d{7}$"),
        IT("意大利", "^(\\+?39)?\\s?3\\d{2} ?\\d{6,7}$"),
        JP("日本", "^(\\+?81|0)\\d{1,4}[ \\-]?\\d{1,4}[ \\-]?\\d{4}$"),
        NO("挪威", "^(\\+?47)?[49]\\d{7}$"),
        BE("比利时", "^(\\+?32|0)4?\\d{8}$"),
        PL("波兰", "^(\\+?48)? ?[5-8]\\d ?\\d{3} ?\\d{2} ?\\d{2}$"),
        BR("巴西", "^(\\+?55|0)\\-?[1-9]{2}\\-?[2-9]{1}\\d{3,4}\\-?\\d{4}$"),
        PT("葡萄牙", "^(\\+?351)?9[1236]\\d{7}$"),
        RU("俄罗斯", "^(\\+?7|8)?9\\d{9}$"),
        RS("塞尔维亚", "^(\\+3816|06)[- \\d]{5,9}$"),
        TR("土耳其", "^(\\+?90|0)?5\\d{9}$"),
        VN("越南", "^(\\+?84|0)?((1(2([0-9])|6([2-9])|88|99))|(9((?!5)[0-9])))([0-9]{7})$"),
        /* 以下是匹配所有手机号校验正则*/
        OT("所有", "^(\\+?0)?([0-9]*[1-9][0-9]*)$");

        /**
         * 国际名称
         */
        private String national;

        /**
         * 正则表达式
         */
        private String regularExp;

        public String getNational() {
            return national;
        }

        public void setNational(String national) {
            this.national = national;
        }

        public String getRegularExp() {
            return regularExp;
        }

        public void setRegularExp(String regularExp) {
            this.regularExp = regularExp;
        }

        MobileRegularExp(String national, String regularExp) {
            this.national = national;
            this.regularExp = regularExp;
        }

        /**
         * 发送短信前，遍历这个枚举，一一校验
         *
         * @param nationalCode
         * @param mobileNumber
         * @return
         */
        public static boolean isMobileNumber(String nationalCode, String mobileNumber) {
            boolean isMobileNumber = false;
            for (MobileRegularExp regularExp : MobileRegularExp.values()) {
                Pattern pattern = Pattern.compile(regularExp.getRegularExp());
                Matcher matcher = pattern.matcher(new StringBuilder().append(nationalCode).append(mobileNumber).toString());
                if (matcher.matches()) {
                    isMobileNumber = true;
                    // 枚举中把最常用的国际区号拍在前面可以减少校验开销
                    break;
                }
            }
            return isMobileNumber;
        }

    }
}

