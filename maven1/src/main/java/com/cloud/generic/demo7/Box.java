package com.cloud.generic.demo7;

/**
 * @author zl
 * @date 2022/5/3 16:24
 */
public class Box<E> {
    private E first;

    public E getFirst() {
        return first;
    }

    public void setFirst(E first) {
        this.first = first;
    }
}
