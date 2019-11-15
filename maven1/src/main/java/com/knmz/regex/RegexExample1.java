package com.knmz.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zl
 * @date 2019/8/13 23:09
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


        // 字母开头 包含字母、数字、下划线、连接符的3-15个字符长度。
//        String pattern3 = "^[a-z0-9_-]{3,15}$";
//        String content3 = "letter_-_123abc";

        // 该标题只包含两个尾随数字，并且出现在行首
//        String pattern3 = "^Chapter [1-9][0-9]{0,1}";
//        String pattern3 = "^Chapter [1-9][0-9]{0,1}$";
//        String content3 = "Chapter 12";

        // \b匹配一个单词边界，即字与空格间的位置。
//        String pattern3 = "\\bCha";
//        String content3 = "Cha";

//        String pattern3 = "ter\\b";
//        String content3 = "ter";

        // \B非单词边界匹配
        String pattern3 = "\\Bapt";
        String content3 = "Chapter";
        boolean result3 = Pattern.matches(pattern3, content3);
        System.out.println("result3: " + result3);

        // 查找重复的单词
//        String pattern4 = "/\b([a-z]+) \1\b/ig";
//        String content4 = "Is is the cost of of gasoline going up up";

        // . 特殊字符在中括号表达式时 如 [.] 只会匹配 .字符，等价于 \.，而非匹配除换行符 \n 外的所有字符。
//        String pattern4 = "[.]";
//        String content4 = ".";

//        String pattern4 = ".";
//        String content4 = "h";

        // ^ 和 [^指定字符串] 之间的区别: ^指的是匹配字符串开始的位置,[^指定字符串] 指的是除指定字符串以外的其他字符串
        // 匹配一个数字的字符串
//        String pattern4 = "(^[0-9])+";
//        String content4 = "2";

        // 匹配有一至多个数字的字符串组合
        String pattern4 = "[^[0-9]]+";
        String content4 = "1154545";

        boolean result4 = Pattern.matches(pattern4, content4);
        System.out.println("result4: " + result4);

        // (?=xox) 和 (?<=xox) 的区别：匹配字符之间的一个虚无的 “空位”。
        // (?=xox) 匹配 xox 之前的空位
        String pattern5 = "(?=xox)";
        String content5 = "abxoxcd";

        // 按指定模式在字符串查找
        Matcher m = Pattern.compile(pattern5).matcher(content5);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
        } else {
            System.out.println("NO MATCH");
        }

        //  (?<=xox) 匹配 xox 之后的空位
//        String pattern5 = "(?<=xox)";
//        String content5 = "abxoxcd";

        boolean result5 = Pattern.matches(pattern5, content5);
        System.out.println("result5: " + result5);

        // 通过正则获取指定字符串，获取活动id

        // false
//        String pattern6 = "^/event/(.*)\\??";

        // 9501927920800
//        String pattern6 = "(?<=/event/).*(?=\\?)";

        // 9501927920800?qd=123
//        String pattern6 = "(?<=/event/).*\\??";

        // 9501927920800
        String pattern6 = "(?<=/event/).{13}";
        String content6 = "https://www.huodongxing.com/event/9501927920800?qd=123";

//        boolean sub = content6.matches(pattern6);
//        boolean result6 = Pattern.matches(pattern6, content6);
//        System.out.println("result6: " + result6);

//        String pattern6 = "once";
//        String content6 = "There once was a man from NewYork";

        String firstString = getMatcherFirst(pattern6, content6);
        System.out.println("firstString: " + firstString);

        List<String> allString = getMatcherAll(pattern6, content6);
        System.out.println("allString: " + allString);

    }

    /**
     * @param source 字符串
     * @param regex 正则表达式
     * 获取满足正则表达式的第一个子串
     */
    private static String getMatcherFirst(String regex, String source) {
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result = matcher.group(0);
        }
        return result;
    }

    /**
     * @param source 字符串
     * @param regex 正则表达式
     * 获取满足正则表达式的所有子串
     */
    private static List<String> getMatcherAll(String regex, String source) {
        List<String> stringList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        int i = 0;
        while (matcher.find()) {
            String subString = matcher.group(i);
            stringList.add(subString);
            i++;
        }
        return stringList;
    }
}
