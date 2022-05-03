package com.cloud.generic.demo7;

/**
 * @author zl
 * @date 2022/5/3 15:40
 */
public class Test07 {
    public static void main(String[] args) {
        Box<Number> box1 = new Box<>();
        box1.setFirst(100);
        showBox(box1);

        Box<Integer> box2 = new Box<>();
        box2.setFirst(100);
        showBox(box2);
    }

    public static void showBox(Box<? extends Number> box) {
        Number first = box.getFirst();
        System.out.println(first);
    }

//    public static void showBox(Box<Integer> box) {
//        Number first = box.getFirst();
//        System.out.println(first);
//    }


}
