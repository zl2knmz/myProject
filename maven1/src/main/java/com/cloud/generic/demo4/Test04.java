package com.cloud.generic.demo4;

/**
 * @author zl
 * @date 2022/5/3 0:45
 */
public class Test04 {
    public static void main(String[] args) {
        ChildFirst<String> stringChildFirst = new ChildFirst<>();
        stringChildFirst.setValue("adb");
        String value = stringChildFirst.getValue();
        System.out.println(value);

        ChildFirst<Integer> intChildFirst = new ChildFirst<>();
        intChildFirst.setValue(123);
        int value1 = intChildFirst.getValue();
        System.out.println(value1);

        System.out.println("------------------------");
        ChildSecond childSecond = new ChildSecond();
        childSecond.setValue(1111111111);
        int intValue = childSecond.getValue();
        System.out.println(intValue);


    }
}
