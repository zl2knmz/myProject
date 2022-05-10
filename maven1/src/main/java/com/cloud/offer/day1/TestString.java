package com.cloud.offer.day1;

/**
 * @author zl
 * @date 2022/5/10 20:48
 */
public class TestString {
    public static void main(String[] args) {
        // intern()
        String str1 = new StringBuilder("ali").append("baba").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        System.out.println("------jvm自带常量值java--------");
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());
    }
}
