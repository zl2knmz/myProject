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
        // quote方法：\Q字符串\E,给字符串添加头\Q和尾\E
//        String q = Pattern.quote(content);
//        System.out.println(q);

        // 匹配数字
        // 1个数字
//        String pattern1 = "[0-9]";
//        String content1 = "94123414";

        /*
        * 一、限定符：* + ？{n} {n,} {n,m}
        */
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

        // 1~n的整数
//        String pattern2 = "[1-9][0-9]*";
//        String content2 = "1099";

        // 0~99的整数
//        String pattern2 = "[0-9]{1,2}";
//        String content2 = "0";

        // 1~99的整数
//        String pattern2 = "[1-9][0-9]?";
//        String pattern2 = "[1-9][0-9]{0,1}";
//        String content2 = "11";

        // 编号为任何位数的章节标题
//        String pattern2 = "Chapter [1-9][0-9]*";
//        String pattern2 = "Chapter [1-9][0-9]?";
        String pattern2 = "Chapter [1-9][0-9]{0,1}";

        String content2 = "Chapter 1";
        boolean result2 = Pattern.matches(pattern2, content2);
        System.out.println("result2: " + result2);

        /*
         * 二、定位符：^ $ \b \B
         */
        // 数字开头，abc结尾
//        String pattern3 = "^[0-9]+abc$";
//        String content3 = "123456abc";

        // 字母开头 包含字母、数字、下划线、连接符的3-15个字符长度。
//        String pattern3 = "^[a-z0-9_-]{3,15}$";
//        String content3 = "letter_-_123abc";

        // 该标题只包含两个尾随数字，并且出现在行首
//        String pattern3 = "^Chapter [1-9][0-9]{0,1}";
//        String pattern3 = "^Chapter [1-9][0-9]{0,1}$";
//        String pattern3 = "\\bCha";
//        String pattern3 = "ter\\b";
        String pattern3 = "\\Bapt";
        String content3 = "Chapter";
        boolean result3 = Pattern.matches(pattern3, content3);
        System.out.println("result3: " + result3);

    }
}
