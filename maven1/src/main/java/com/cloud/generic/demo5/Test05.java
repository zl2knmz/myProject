package com.cloud.generic.demo5;

/**
 * @author zl
 * @date 2022/5/3 15:19
 */
public class Test05 {
    public static void main(String[] args) {
        Apple apple = new Apple();
        String key = apple.getKey();
        System.out.println(key);

        System.out.println("--------------------------------");

        Pair<String, Integer> pair = new Pair<>("count", 100);

        String key1 = pair.getKey();
        int value = pair.getValue();
        System.out.println(key1 + "=" + value);


    }
}
