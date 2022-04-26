package com.cloud.algorithm;

/**
 * @author zl
 * @date 2020/1/14 16:56
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 */
public class Solution_2 {
    public boolean isPowerOfTwo(int n) {
        boolean result = false;
        if (1 == n) {
            result = true;
            System.out.println("2^0 = 1");
            return result;
        }

        if (2 == n) {
            result = true;
            System.out.println("2^1 = 2");
            return result;
        }

        int num = 2;
        int i = 1;
        while (num <= n) {
            num = 2 * num;
            i++;
            if (n == num) {
                System.out.println("2^" + i + " = " + n);
                result = true;
                break;
            }
        }
        return result;
    }
}
