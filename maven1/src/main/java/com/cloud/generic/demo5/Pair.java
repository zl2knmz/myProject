package com.cloud.generic.demo5;

/**
 * 泛型接口的实现类，是一个泛型类，那么要保证实现泛型接口的泛型类标识包含泛型接口的泛型标识
 *
 * @author zl
 * @date 2022/5/3 15:28
 */
public class Pair<T, E> implements Generator<T> {
    private T key;
    private E value;

    public Pair(T key, E value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public T getKey() {
        return key;
    }

    public E getValue() {
        return value;
    }
}
