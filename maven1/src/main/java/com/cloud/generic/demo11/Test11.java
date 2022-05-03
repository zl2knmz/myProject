package com.cloud.generic.demo11;

import java.lang.reflect.Constructor;

/**
 * @author zl
 * @date 2022/5/3 15:40
 */
public class Test11 {
    public static void main(String[] args) throws Exception{
//        Class<Person> personClass = Person.class;
//        Constructor<Person> constructor = personClass.getConstructor();
//        Person person = constructor.newInstance();

        Class personClass = Person.class;
        Constructor constructor = personClass.getConstructor();
        Object o = constructor.newInstance();
    }
}
