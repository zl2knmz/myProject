package com.cloud.meiju.demo0;

import org.junit.Test;

/**
 * @author zl
 * @date 2023/12/8 17:52
 */
enum Person {
    MAN, WOMAN, NON
}

public class PersonTest {
    Person person = Person.WOMAN;

    public static void main(String[] args) {
        Person[] values = Person.values();

        for (Person value : values) {
            System.out.println("for:" + value);
            System.out.println("for name:" + value.name());
            if (Person.valueOf("MAN") == value) {
                System.out.println("=:" + value);
            }

            if (Person.valueOf("MAN").equals(value)) {
                System.out.println("equals:" + value);
            }

        }
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

