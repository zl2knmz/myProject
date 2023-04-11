package com.cloud.test;

import java.math.BigDecimal;

/**
 * 金额类： BigDecimal, Long(单位：分), Double
 *
 * @author zl
 * @date 2023/4/11 16:29
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal ticketPrice = new BigDecimal("19.123");
        // BigDecimal转long  longValue()
        System.out.println(ticketPrice.longValue());

        // 乘以100 multiply() 或者 movePointRight() 小数点右移2位
        System.out.println((ticketPrice.movePointRight(2).longValue()));
        System.out.println((ticketPrice.multiply(new BigDecimal(100)).longValue()));

        // 比较大小 -1: 小于、 0: 等于、 1: 大于
        int compareTo = ticketPrice.compareTo(BigDecimal.ZERO);
        System.out.println(compareTo);


        BigDecimal num1 = new BigDecimal(0.005);
        BigDecimal num2 = new BigDecimal(1000000);
        BigDecimal num3 = new BigDecimal(-1000000);
        // 尽量用字符串的形式初始化
        BigDecimal num12 = new BigDecimal("0.005");
        BigDecimal num22 = new BigDecimal("1000000");
        BigDecimal num32 = new BigDecimal("-1000000");

        // 加法 add()
        BigDecimal result1 = num1.add(num2);
        BigDecimal result12 = num12.add(num22);
        System.out.println("加法用value结果：" + result1);
        System.out.println("加法用string结果：" + result12);

        // 减法 subtract()
        BigDecimal result2 = num1.subtract(num2);
        BigDecimal result22 = num12.subtract(num22);
        System.out.println("减法value结果：" + result2);
        System.out.println("减法用string结果：" + result22);

        // 乘法 multiply()
        BigDecimal result3 = num1.multiply(num2);
        BigDecimal result32 = num12.multiply(num22);
        System.out.println("乘法用value结果：" + result3);
        System.out.println("乘法用string结果：" + result32);

        // 绝对值 abs()
        BigDecimal result4 = num3.abs();
        BigDecimal result42 = num32.abs();
        System.out.println("绝对值用value结果：" + result4);
        System.out.println("绝对值用string结果：" + result42);

        // 除法 divide()
        BigDecimal result5 = num2.divide(num1, 20, BigDecimal.ROUND_HALF_UP);
        BigDecimal result52 = num22.divide(num12, 20, BigDecimal.ROUND_HALF_UP);
        System.out.println("除法用value结果：" + result5);
        System.out.println("除法用string结果：" + result52);

    }
}
