package com.cloud.generic.demo6;

import com.cloud.generic.choujiang.ProductGetter;

import java.util.ArrayList;

/**
 * @author zl
 * @date 2022/5/3 15:40
 */
public class Test06 {
    public static void main(String[] args) {
        ProductGetter<Integer> productGetter = new ProductGetter<>();
        int[] products = {1000,2000,3000,4000,5000};
        for (int i: products) {
            productGetter.addProduct(i);
        }
        // 泛型类的成员方法的调用
        Integer product = productGetter.getProduct();
        System.out.println(product + "\t" + product.getClass().getSimpleName());
        System.out.println("---------------------------------");

        ArrayList<String> strList = new ArrayList<>();
        strList.add("苹果手机1");
        strList.add("笔记本电脑1");
        strList.add("扫地机器人1");
        // 泛型方法的调用，类型是通过调用方法的时候来指定。
        String product1 = productGetter.getProduct(strList);
        System.out.println(product1 + "\t" + product1.getClass().getSimpleName());
        System.out.println("---------------------------------");

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(100);
        intList.add(200);
        intList.add(300);
        // 泛型方法的调用，类型是通过调用方法的时候来指定。
        Integer product2 = productGetter.getProduct(intList);
        System.out.println(product2 + "\t" + product2.getClass().getSimpleName());

        System.out.println("---------------------------------");

        // 调用多个泛型类型的静态泛型方法
        ProductGetter.printType(100, "java", true);
        ProductGetter.printType(false, false, true);
        System.out.println("---------------------------------");

        // 可变参数的泛型方法的调用
        ProductGetter.print(1,2,3,4,5);
        ProductGetter.print("a","b","c");

    }
}
