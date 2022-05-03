package com.cloud.generic.demo8;

import java.util.ArrayList;

/**
 * @author zl
 * @date 2022/5/3 16:51
 */
public class TestUp {

    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Cat> cats = new ArrayList<>();
        ArrayList<MiniCat> miniCats = new ArrayList<>();

        cats.addAll(miniCats);
        cats.addAll(cats);
//        cats.addAll(animals);

//        showAnimal(animals);
        showAnimal(cats);
        showAnimal(miniCats);
    }

    /**
     * 泛型上限通配符，传递的集合类型，只能是Cat或Cat的子类类型。
     */
    public static void showAnimal(ArrayList<? extends  Cat> list){
        // 不能添加元素
//        list.add(new Animal());
//        list.add(new Cat());
//        list.add(new MiniCat());
        for (int i = 0; i < list.size(); i++) {
            Cat cat = list.get(i);
        }
    }
}
