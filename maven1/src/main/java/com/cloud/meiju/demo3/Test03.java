package com.cloud.meiju.demo3;

/**
 * @author zl
 * @date 2022/5/6 10:54
 */
public class Test03 {
    public static void main(String[] args) {
        System.out.println("--------------EnumsUtil-获取枚举--------------------");
        Constants.LiveWatermark_Enum clearEnum = EnumsUtil.valueOf(Constants.LiveWatermark_Enum.class, "0");
        System.out.println(clearEnum);

        System.out.println("--------------EnumsUtil-获取枚举集合--------------------");
        IEnum[] values = EnumsUtil.values(Constants.LiveWatermarkRule_Enum.class);
        for (IEnum value : values) {
            System.out.println(value);
        }

        System.out.println("---------------枚举常用方法------valueOf--------------");
        Constants.LiveWatermark_Enum enumMethod1 = Constants.LiveWatermark_Enum.valueOf("CLEAR_WATER_MARK");
        System.out.println(enumMethod1);

        System.out.println("---------------枚举常用方法-----values---------------");
        IEnum[] enumMethod2 = Constants.LiveWatermark_Enum.values();
        for (IEnum iEnum : enumMethod2) {
            System.out.println(iEnum);
        }

        System.out.println("---------------枚举常用方法----toString---返回当前枚举类对象常量的名称-------------");
        System.out.println(Constants.LiveWatermark_Enum.OBS_RIGHT_UP.name());
        System.out.println(Constants.LiveWatermark_Enum.OBS_RIGHT_UP);
        System.out.println(Constants.LiveWatermark_Enum.OBS_RIGHT_UP.toString());
    }
}
