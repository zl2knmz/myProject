package com.cloud.generic.demo10;

import java.lang.reflect.Array;

/**
 * @author zl
 * @date 2022/5/3 22:10
 */
public class Fruit<T> {
    private T[] array;

    public Fruit(Class<T> clz, int length) {
        // 通过Array.newInstance创建泛型数组
        this.array = (T[]) Array.newInstance(clz, length);
    }

    /**
     * 填充数组
     *
     * @param index
     * @param item
     */
    public void put(int index, T item) {
        array[index] = item;
    }

    /**
     * 获取数组元素
     *
     * @param index
     * @return
     */
    public T get(int index) {
        return array[index];
    }

    /**
     * 获取数组元素
     *
     * @return T[]
     */
    public T[] getArray() {
        return array;
    }
}
