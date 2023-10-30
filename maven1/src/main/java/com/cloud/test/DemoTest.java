package com.cloud.test;

/**
 * @author zl
 * @date 2023/10/24 18:58
 */
public class DemoTest {
    public static void main(String[] args) {
        int random = (int) (Math.random() * 5) + 1;
        System.out.println(random);

        long random1 = (int) (Math.random() * 20) + 3L;
        System.out.println(random1);
    }
}
