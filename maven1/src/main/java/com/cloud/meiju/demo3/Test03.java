package com.cloud.meiju.demo3;

/**
 * @author zl
 * @date 2022/5/6 10:54
 */
public class Test03 {
    public static void main(String[] args) {
        System.out.println("---------------获取枚举--------------------");
        Constants.LiveWatermark_Enum clearEnum = EnumsUtil.valueOf(Constants.LiveWatermark_Enum.class, "0");
        System.out.println(clearEnum);

        System.out.println("---------------获取枚举集合--------------------");
        IEnum[] values = EnumsUtil.values(Constants.LiveWatermarkRule_Enum.class);
        for (IEnum value : values) {
            System.out.println(value);
        }
    }
}
