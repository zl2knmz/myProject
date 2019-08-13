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
        String pattern = ".*runoob.*";
        boolean result = Pattern.matches(pattern, content);
        System.out.println("result: " + result);

        // 匹配数字
//        String content1 = "123456";
        // 多个数字
//        String pattern1 = "[0-9]+";
        String content1 = "9";
        // 单个数字
        String pattern1 = "[0-9]";
        boolean result1 = Pattern.matches(pattern1, content1);
        /*String q = Pattern.quote(content1);
        System.out.println(q);*/
        System.out.println("result1: " + result1);

        // 通配符
        /*String content2 = "123456abc";
        // 数字开头，abc结尾
        String pattern2 = "^[0-9]+abc$";*/
        //
        String content2 = "letter_-_123abc";
        // 字母开头 包含字母、数字、下划线、连接符的3-15个字符长度。
        String pattern2 = "^[a-z0-9_-]{3,15}$";
        boolean result2 = Pattern.matches(pattern2, content2);
        System.out.println("result2: " + result2);
    }
}
