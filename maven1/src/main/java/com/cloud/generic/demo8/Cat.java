package com.cloud.generic.demo8;

/**
 * @author zl
 * @date 2022/5/3 16:35
 */
public class Cat extends Animal {
    public int age;

    public Cat(String name, int age) {
        super(name);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
