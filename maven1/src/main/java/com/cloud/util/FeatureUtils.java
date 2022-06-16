package com.cloud.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        // 2进制
        int radix = 2;

        char[] ats = new char[maxLength];
        if (open) {
            // 开启featured
            Arrays.fill(ats, '0');
            ats[maxLength - featured] = '1';
            int one = Integer.parseInt(new String(ats), radix);
            result = (value | one);
            System.out.println("open featured=" + featured);
        } else {
            // 关feature
            Arrays.fill(ats, '1');
            ats[maxLength - featured] = '0';
            int zero = Integer.parseInt(new String(ats), radix);
            result = (value & zero);
            System.out.println("close featured=" + featured);
        }
        return result;
    }

    /**
     * 查看活动表 feature 开了哪些开关
     *
     * @param featured 数据库featured值
     * @return list 开了哪些位数
     */
    public static List<Integer> getFeaturedList(long featured) {
        int maxLength = 29;
        List<Integer> featureList = new ArrayList<>();
        String result = Long.toBinaryString(featured);
        char[] tmp = result.toCharArray();
        char[] bytes = new char[tmp.length];
        for (int i = tmp.length - 1, j = 0; i >= 0; i--, j++) {
            bytes[j] = tmp[i];
        }
        for (int i = 0; i < bytes.length; i++) {
            if ("1".equals(String.valueOf(bytes[i])) && i < maxLength){
                featureList.add(i + 1);
            }
        }
        if (featureList.size() == 0) {
            featureList.add(0);
        }
        return featureList;
    }


    public static void main(String[] args) {
        // 2048 22 2099200 22=2097152 27=67108864
        int a = getActivityFeatured(2048, 27, true);
        System.out.println(a);
        int b = getActivityFeatured(67110912, 27, false);
        System.out.println(b);
        System.out.println(getFeaturedList(2099200));
    }
}
