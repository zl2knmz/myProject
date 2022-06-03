package com.cloud.offer.day3;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 利用LinkedHashMap实现LRU算法
 *
 * @author zl
 * @date 2022/5/31 23:59
 */
public class LRUCacheDemo1<K, V> extends LinkedHashMap<K, V> {
    /**
     * 缓存坑位
     */
    private int capacity;

    /**
     * accessOrder  the ordering mode -
     * true: for access-order
     * false: for insertion-order
     */
    public LRUCacheDemo1(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {

        LRUCacheDemo1 lruCacheDemo1 = new LRUCacheDemo1(3);
        lruCacheDemo1.put(1, "a");
        lruCacheDemo1.put(2, "b");
        lruCacheDemo1.put(3, "c");
        System.out.println(lruCacheDemo1.keySet());

        lruCacheDemo1.put(4, "d");
        System.out.println(lruCacheDemo1.keySet());

        lruCacheDemo1.put(3, "c");
        System.out.println(lruCacheDemo1.keySet());
        lruCacheDemo1.put(3, "c");
        System.out.println(lruCacheDemo1.keySet());
        lruCacheDemo1.put(3, "c");
        System.out.println(lruCacheDemo1.keySet());

        lruCacheDemo1.put(5, "x");
        System.out.println(lruCacheDemo1.keySet());
    }
}
