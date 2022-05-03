package com.cloud.generic.demo9;

import java.util.List;

/**
 * @author zl
 * @date 2022/5/3 21:27
 */
public class Erasure<T extends Number> {
    private  T key;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public <T extends List> T show(T t){
        return t;
    }
}
