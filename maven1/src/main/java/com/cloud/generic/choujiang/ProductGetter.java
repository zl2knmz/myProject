package com.cloud.generic.choujiang;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author zl
 * @date 2022/5/3 0:20
 */
public class ProductGetter<T> {
    Random random = new Random();
    /**
     * 奖品
     */
    private T product;

    /**
     * 奖品池
     */
    ArrayList<T> list = new ArrayList<>();

    /**
     * 添加奖品
     */
    public void addProduct(T t) {
        list.add(t);
    }

    /**
     * 抽奖
     */
    public T getProduct() {
        product = list.get(random.nextInt(list.size()));
        return product;
    }
}
