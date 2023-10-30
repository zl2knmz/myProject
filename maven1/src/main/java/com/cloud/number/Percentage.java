package com.cloud.number;

import cn.hutool.core.util.NumberUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 百分比
 *
 * @author zl
 * @date 2023/8/25 14:46
 */
public class Percentage {


    /**
     * 计算一个数是另一个数的百分之多少。
     *
     * @param number1 被比较的数
     * @param number2 作为比较基准的数
     * @return 百分比值
     */
    public static double calculatePercentage(double number1, double number2) {
        if (number2 == 0) {
            System.out.println("不能将一个数除以零。");
            // Not a Number
            return Double.NaN;
        }

        return (number1 / number2) * 100;
    }

    /**
     * 计算一个数是另一个数的百分之多少。
     *
     * @param d 被比较的数
     * @param e 作为比较基准的数
     * @return 百分比值
     */
    public static String percnet(double d, double e) {
        double p = d / e;
        DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();
        // 00表示小数点2位
        nf.applyPattern("00%");
        // 2表示精确到小数点后2位
        nf.setMaximumFractionDigits(2);
        return nf.format(p);
    }

    /**
     * 糊涂工具包网址：https://hutool.cn/docs/#/
     * 计算百分比
     *
     * @param number1 被比较的数
     * @param number2 作为比较基准的数
     * @return 百分比值
     */
    public static String hutoolPercent(double number1, double number2) {
        double num = NumberUtil.div(number1, number2);
        String percent2 = NumberUtil.decimalFormat("#.##%", num);
        return percent2;
    }

    public static void main(String[] args) {
        // 两个要计算百分比的数
        double number1 = 5.0;
        double number2 = 120;

        // 计算百分比
        double percent = calculatePercentage(number1, number2);
        System.out.println(percent);

        String percent1 = percnet(number1, number2);
        System.out.println(percent1);


        String percent2 = hutoolPercent(number1, number2);
        System.out.println(percent2);
    }
}
