package com.cloud.meiju.demo0;

import org.junit.Test;

//import java.lang.Enum;
//import java.lang.Serializable;
//import java.lang.Comparable;

/**
 * enum 定义的枚举类默认继承了 java.lang.Enum 类，并实现了 java.lang.Serializable 和 java.lang.Comparable 两个接口。
 * <p>
 * values(), ordinal() 和 valueOf() 方法位于 java.lang.Enum 类中：
 * values() 返回枚举类中所有的值。
 * ordinal()方法可以找到每个枚举常量的索引，就像数组索引一样。
 * valueOf()方法返回指定字符串值的枚举常量。
 *
 * @author zl
 * @date 2023/12/8 17:52
 */
enum Person {
    /**
     *
     */
    MAN, WOMAN, NON
}

public class PersonTest {
    Person person = Person.WOMAN;

    public static void main(String[] args) {
        // 返回枚举类中所有的值
        Person[] values = Person.values();

        for (Person value : values) {
            System.out.println("for:" + value);
            System.out.println("for name:" + value.name());
            // valueOf()方法返回指定字符串值的枚举常量。
            if (Person.valueOf("MAN") == value) {
                System.out.println("=:" + value);
            }

            if (Person.valueOf("MAN").equals(value)) {
                System.out.println("equals:" + value);
            }

            // ordinal()方法 查看索引
            System.out.println(value + " at index " + value.ordinal());
        }

        // 枚举下标相减
        int i = Person.WOMAN.compareTo(Person.MAN);
        System.out.println(i);

    }

    @Test
    public void change() {
        switch (person) {
            case MAN:
                person = Person.WOMAN;
                System.out.println(person.name());
                break;
            case WOMAN:
                person = Person.MAN;
                System.out.println(person.name());
                break;
            default:
                System.out.println(Person.NON.name());
                break;
        }
    }

}

