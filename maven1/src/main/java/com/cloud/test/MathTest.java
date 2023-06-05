package com.cloud.test;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * @author zl
 * @date 2023/6/5 14:57
 */
public class MathTest {

    /**
     * 当前实际数字都放大 2 倍*
     * 1万以内的直接展示数据，超过1万的展示 x.xw
     */
    @Test
    public void showUseSocialNum() {
        int num = 16205;

        String count = "0";
        // 显示单位
        int unit = 10000;
        int showNum = 2 * num;
        System.out.println("showNum=" + showNum);

        if (showNum < unit) {
            count = String.valueOf(showNum);
        } else {
            DecimalFormat df = new DecimalFormat("0.0");

            float number = (float) showNum / unit;
            count = df.format(number) + "w";
        }

        System.out.println(count);
    }


}
