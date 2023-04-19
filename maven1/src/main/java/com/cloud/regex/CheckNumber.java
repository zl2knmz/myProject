package com.cloud.regex;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参考文档
 *
 * @author zl
 * @https://blog.csdn.net/qq_44750696/article/details/121230073
 * @date 2023/4/19 17:11
 */
public class CheckNumber {
    public static void main(String[] args) {
        String str = "124314132430986741234567890124";
        boolean matches = str.matches("[0-9]*");
        System.out.println(matches);


        boolean result = CheckNumber.isNumber(str);
        System.out.println("1---" + result);
        
        boolean result2 = CheckNumber.isNumber2(str);
        System.out.println("2---" + result2);

        boolean result3 = CheckNumber.isNumber3(str);
        System.out.println("3---" + result3);

        boolean result4 = CheckNumber.isNumber4(str);
        System.out.println("4---" + result4);

        boolean result5 = CheckNumber.isNumber5(str);
        System.out.println("5---" + result5);

        boolean result6 = CheckNumber.isNumber6(str);
        System.out.println("6---" + result6);

        boolean result7 = CheckNumber.isNumber7(str);
        System.out.println("7---" + result7);

    }

    /**
     * 判断是否为数字可以使用工具类 StringUtils
     * 通过方法 isNumeric 进行判断是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumber(String str) {
        return StringUtils.isNumeric(str);
    }

    /**
     * 使用ACSII码
     *
     * @param str
     * @return
     */
    public static boolean isNumber2(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                return false;
            }
        }
        return true;
    }

    /**
     * 使用Java自带的函数，这个方式三的原理和方式一中的StringUtils.isNumeric()是一样的。
     *
     * @param str
     * @return
     */
    public static boolean isNumber3(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // 根据阿里巴巴代码规范，将Pattern设置为全局常量
    // 通过 -?[0-9]+(\\\\.[0-9]+)? 进行匹配是否为数字
    private static Pattern pattern = Pattern.compile("-?[0-9]+(\\\\.[0-9]+)?");

    /**
     * 通过正则表达式判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumber4(String str) {
        // 通过Matcher进行字符串匹配
        Matcher m = pattern.matcher(str);
        // 如果正则匹配通过 m.matches() 方法返回 true ，反之 false
        return m.matches();
    }

    public static boolean isNumber5(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isNumber6(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isNumber7(String str) {
        // ?:0或1个, *:0或多个, +:1或多个
        return str.matches("-?[0-9]+.?[0-9]*");

    }


}
