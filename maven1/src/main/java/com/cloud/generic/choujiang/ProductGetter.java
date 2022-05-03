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

    /**
     * 定义泛型方法
     *
     * @param list 参数
     * @param <E>  泛型标识，具体类型，由调用方法的时候来指定。
     * @return
     */
//    public <T> T getProduct(ArrayList<T> list){
    public <E> E getProduct(ArrayList<E> list) {
        return list.get(random.nextInt(list.size()));
    }

    /**
     * 静态的泛型方法，采用多个泛型类型
     *
     * @param t
     * @param e
     * @param k
     * @param <T>
     * @param <E>
     * @param <K>
     * @return
     */
    public static <T, E, K> void printType(T t, E e, K k) {
        System.out.println(t + "\t" + t.getClass().getSimpleName());
        System.out.println(e + "\t" + e.getClass().getSimpleName());
        System.out.println(k + "\t" + k.getClass().getSimpleName());

    }

    /**
     * 泛型可变参数的定义
     *
     * @param e
     * @param <E>
     */
    public static <E> void print(E... e) {
        for (int i = 0; i < e.length; i++) {
            System.out.println(e[i]);
        }
    }

}
