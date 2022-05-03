package com.cloud.meiju;

/**
 * @author zl
 * @date 2022/5/3 23:26
 */
public class Test01 {
    public static void main(String[] args) {
//        new EnumTest();

        System.out.println(EnumTest.ONE.name());
        System.out.println(EnumTest.ONE.ordinal());
        System.out.println(EnumTest.ONE.getDeclaringClass());
        System.out.println(EnumTest.ONE.getClass());
        System.out.println(EnumTest.valueOf("ONE"));

        choice(EnumTest.ONE);

        System.out.println(EnumTest.ONE.getSay());
        System.out.println(EnumTest.TWO.getSay());

    }

    public static void choice(EnumTest enumTest) {
        switch (enumTest) {
            case ONE:
                System.out.println("one");
                break;
            case TWO:
                System.out.println("two");
                break;
            default:
                System.out.println("no");
        }
    }
}
