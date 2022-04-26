package com.cloud.util.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * guava实现布隆过滤器
 *
 * @author zl
 * @date 2019/10/29 11:57
 */
public class GuavaBloomFilter {
    /**
     * 预计要插入多少数据
     */
    private static int size = 1000000;

    /**
     * 期望的误判率
     */
    private static double fpp = 0.01;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    public static void main(String[] args) {
        // 插入数据
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        // 测试误判率
        int count = 0;
        int num = 2 * size;
        for (int i = size; i < num; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("总共的误判数:" + count);
    }
}
