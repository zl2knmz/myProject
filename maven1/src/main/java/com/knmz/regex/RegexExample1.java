package com.knmz.regex;

import java.util.regex.Pattern;

/**
 * @Author: zl
 * @Date: 2019/8/13 23:09
 */
public class RegexExample1 {
    public static void main(String args[]) {
        // 匹配字符串
        String content = "I am noob from runoob.com.";
        // 包含runoob的字符串
        String pattern = ".*runoob.*";
        boolean result = Pattern.matches(pattern, content);
        System.out.println("result: " + result);

        // 匹配数字
        // 1个数字
//        String pattern1 = "[0-9]";
//        String content1 = "94123414";

        // 0~1个数字, ? <=> {0,1}
//        String pattern1 = "[0-9]?";
//        String pattern1 = "[0-9]{0,1}";
//        String content1 = "9";

        // 1~n个数字, + <=> {1,}
//        String pattern1 = "[0-9]+";
//        String pattern1 = "[0-9]{1,}";
//        String content1 = "123456";

        // 0~n个数字, * <=> {0,}
//        String pattern1 = "[0-9]*";
        String pattern1 = "[0-9]{0,}";
        String content1 = "94123414";

        boolean result1 = Pattern.matches(pattern1, content1);
        System.out.println("result1: " + result1);

        // quote方法：\Q字符串\E,给字符串添加头\Q和尾\E
//        String q = Pattern.quote(content1);
//        System.out.println(q);

        // 通配符
        // 数字开头，abc结尾
//        String pattern2 = "^[0-9]+abc$";
//        String content2 = "123456abc";

        // 字母开头 包含字母、数字、下划线、连接符的3-15个字符长度。
        String pattern2 = "^[a-z0-9_-]{3,15}$";
        String content2 = "letter_-_123abc";
        boolean result2 = Pattern.matches(pattern2, content2);
        System.out.println("result2: " + result2);


    }
}
