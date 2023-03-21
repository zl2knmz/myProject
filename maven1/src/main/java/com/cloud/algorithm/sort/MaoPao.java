package com.cloud.algorithm.sort;


/**
 *
 * 冒泡排序
 * https://blog.csdn.net/qq_43794633/article/details/121612149
 *
 * @author zl
 * @date 2023/3/21 22:03
 */
public class MaoPao {
    public static void main(String[] args) {
        int[] arr = {4, 6, 12, 54, 67, 1, 90, 24, 36};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }

            System.out.println("----------排序次数"+i);
            for (int j : arr) {
                System.out.println(j);
            }
        }

        for (int j : arr) {
            System.out.println(j);
        }
    }
}
