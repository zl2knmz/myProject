package com.cloud.generic.demo8;

/**
 * @author zl
 * @date 2022/5/3 16:35
 */
public class Animal {
    public String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}
