package com.cloud.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zl
 * @date 2022/12/2 11:19
 */
public class Java8OptionalTest {
    public static void main(String args[]) {

        Java8OptionalTest java8Tester = new Java8OptionalTest();
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(java8Tester.sum(a, b));

        List<Long> longList = new ArrayList<>();
//        List<Long> longList = null;
        Optional<List<Long>> c = Optional.ofNullable(longList);
        System.out.println(c.orElse(null));
        System.out.println(c.get());
        System.out.println(c.isPresent());

        Optional<List<Long>> d = Optional.of(longList);
        System.out.println(d.get());
        System.out.println(d.isPresent());
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {

        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));
        System.out.println(value1);

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }

    @Test
    public void test1() {
        String str = null;
        String str1 = "123";
        String str2 = "456";
        Optional<String> stringOptional = Optional.of(str2);
//        Optional<String> stringOptional = Optional.ofNullable(str);
        boolean present = stringOptional.isPresent();
        System.out.println(present);
        if (present) {
            String s = stringOptional.get();
            System.out.println(s);
        }

        System.out.println("1111111111");

        String s1 = stringOptional.orElse("1111");
        System.out.println(s1);

        String str21 = null;
        String str3 = Optional.ofNullable(str21).orElse("2222");
        System.out.println(str3);

        String str4 = null;
        Optional<String> optional = Optional.ofNullable(str4);
        // java.util.NoSuchElementException: No value present
        System.out.println("str4=" + optional.get());
    }
}
