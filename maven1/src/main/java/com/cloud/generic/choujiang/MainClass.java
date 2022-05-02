package com.cloud.generic.choujiang;

/**
 * @author zl
 * @date 2022/5/3 0:26
 */
public class MainClass {
    public static void main(String[] args) {
        ProductGetter<String> stringProductGetter = new ProductGetter<>();
        String[] strProducts = {"iPhone", "huaWei", "扫地机器人", "咖啡机"};
        for (String str : strProducts) {
            stringProductGetter.addProduct(str);
        }
        // 抽奖
        String product1 = stringProductGetter.getProduct();
        System.out.println("抽中了: " + product1);

        System.out.println("----------------------------");

        ProductGetter<Integer> integerProductGetter = new ProductGetter<>();
        int[] intProducts = {1000, 2000, 3000, 5000};
        for (int str : intProducts) {
            integerProductGetter.addProduct(str);
        }
        // 抽奖
        int product2 = integerProductGetter.getProduct();
        System.out.println("抽中了: " + product2);
    }
}
