package com.cloud.generic.demo4;

/**
 * @author zl
 * @date 2022/5/3 0:42
 */
public class Parent<E> {
    private E value;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
