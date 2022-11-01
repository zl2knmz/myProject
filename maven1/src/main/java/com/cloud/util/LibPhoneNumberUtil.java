package com.cloud.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

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

}
