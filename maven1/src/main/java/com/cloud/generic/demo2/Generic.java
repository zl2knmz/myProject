package com.cloud.generic.demo2;

/**
 * 泛型类的定义
 * @param <T> 泛型标识--类型形参
 *           T 创建对象的时候里指定具体的数据类型
 * @author zl
 * @date 2022/5/3 22:47
 */
public class Generic<T> {
    /**
     * T,是由外部使用类的时候来指定。
     */
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}