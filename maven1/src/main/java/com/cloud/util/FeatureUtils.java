package com.cloud.util;

import java.util.Arrays;

/**
 * @author zl
 * @date 2022/5/7 11:24
 */
public class FeatureUtils {
    /**
     * 设置活动feature值
     *
     * @param value    数据库featured值
     * @param open     0-关掉 1-开启
     * @param featured 开关位数 22-设置回放，27-活动号会员回放
     */
    public static int getActivityFeatured(int value, int featured, boolean open) {
        int result = 0;
        int maxLength = 27;

        char[] ats = new char[maxLength];
        if (open) {
            // 开启featured
            Arrays.fill(ats, '0');
            ats[maxLength - featured] = '1';
            int a = Integer.parseInt(new String(ats), 2);
            result = (value | a);
            System.out.println("open featured=" + featured);
        } else {
            // 关feature
            Arrays.fill(ats, '1');
            ats[maxLength - featured] = '0';
            int a = Integer.parseInt(new String(ats), 2);
            result = (value & a);
            System.out.println("close featured=" + featured);
        }
        return result;
    }

    public static void main(String[] args) {
        // 2048 22 2099200 22=2097152 27=67108864
        int a = getActivityFeatured(0, 27, true);
        System.out.println(a);
        int b = getActivityFeatured(0, 27, false);
        System.out.println(b);
    }
}
