package com.cloud.generic.demo5;

/**
 * 泛型接口
 *
 * @author zl
 * @date 2022/5/3 15:22
 */
public interface Generator<T> {
    /**
     * getKey
     * @return T
     */
    T getKey();
}

