package com.cloud.thread.demo;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zl
 * @date 2023/3/2 16:48
 */
public class Demo1 {
    private final AtomicInteger COUNT = new AtomicInteger(0);

    public ThreadPoolExecutor initThreadPool() {
        LinkedBlockingQueue queue = new LinkedBlockingQueue<>(10);
        final ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("myThread-%d").setDaemon(true).build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS,
                queue,
                factory,
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(Thread.currentThread().getName() + "-reject-" + COUNT.incrementAndGet());
                    }
                });
        return threadPoolExecutor;
    }

    /**
     * 获取线程安全的List我们可以通过Vector、Collections.synchronizedList()方法和CopyOnWriteArrayList三种方式
     * 读多写少的情况下，推荐使用CopyOnWriteArrayList方式
     * 读少写多的情况下，推荐使用Collections.synchronizedList()的方式
     * ————————————————
     * 原文链接：https://blog.csdn.net/weixin_45668482/article/details/117396603
     */
    public List<Integer> sumSync(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // ArrayList 线程不安全
//        List<Integer> sumList = new ArrayList<>();
        // 线程安全的List： Vector、Collections.synchronizedList()方法和CopyOnWriteArrayList三种方式
//        Vector<Integer> sumList = new Vector<>();
//        CopyOnWriteArrayList<Integer> sumList = new CopyOnWriteArrayList<>();
        List<Integer> sumList = Collections.synchronizedList(new ArrayList<>());

//        List<List<Integer>> parts = SplitListUtils.split(list, 100);
        List<List<Integer>> parts = Lists.partition(list, 100);

        ThreadPoolExecutor threadPoolExecutor = this.initThreadPool();
        for (List<Integer> part : parts) {
            threadPoolExecutor.execute(() -> {
                sumList.addAll(part);
//                System.out.println("===>" + Thread.currentThread().getName());
            });
        }
        return sumList;
    }

    public static void main(String[] args) {
        int size = 1000;

        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum = sum + i;
        }
        System.out.println("sum ===> " + sum);

        List<Integer> list = new Demo1().sumSync(size);
        System.out.println("======list size=======" + list.size());

        int result = 0;
        for (Integer integer : list) {
            result = result + integer;
        }
        System.out.println("result ===> " + result);
    }
}
