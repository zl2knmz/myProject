package com.cloud.generic.demo8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zl
 * @date 2022/5/3 16:51
 */
public class TestDown {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Cat> cats = new ArrayList<>();
        ArrayList<MiniCat> miniCats = new ArrayList<>();

        showAnimal(animals);
        showAnimal(cats);
//        showAnimal(miniCats);

    }

    /**
     * 类型通配符下限，要求集合只能是Cat或Cat的父类类型
     */
    public static void showAnimal(List<? super Cat> list) {
        // 可以添加元素（子类类型）
//        list.add(new Cat());
//        list.add(new MiniCat());
        for (Object o : list) {
            System.out.println(o);
        }

    }


}
