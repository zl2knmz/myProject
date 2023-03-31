package com.cloud.test;

/**
 * @author zl
 * @date 2023/3/31 13:48
 */
public class OperationTest {
    public static void main(String[] args) {
        int a = 1024;
        // 移位运算 左移一位（乘以2）
        int b = a << 1;
        // 移位运算 右移一位（除以2）
        int c = a >> 1;
        System.out.println("a=" + a + ",b=" + b + ",c=" + c);
    }
}
