package com.cloud.thread.demo;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程处理List数据
 *
 * @author zl
 * @date 2023/3/2 16:48
 */
public class Demo1 {
    private final AtomicInteger COUNT = new AtomicInteger(0);

    public ThreadPoolExecutor initThreadPool() {
        LinkedBlockingQueue queue = new LinkedBlockingQueue<>(100);
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

    /**
     * 多线程减数
     * AtomicInteger 线程安全的 保证原子性
     */
    public AtomicInteger subSync(int size) {
        AtomicInteger count = new AtomicInteger(100);
        ThreadPoolExecutor threadPoolExecutor = this.initThreadPool();
        for (int i = 0; i < size; i++) {
            threadPoolExecutor.execute(() -> {
                count.getAndDecrement();
                System.out.println("===>" + Thread.currentThread().getName());
            });
        }
        return count;
    }

//    private static final int[] count = {100};
    /**
     * 多线程减数
     * int[]
     */
    public int subSync1(int size) {
        final int[] count = {80};
//        Object o = new Object();
        ThreadPoolExecutor threadPoolExecutor = this.initThreadPool();
        for (int i = 0; i < size; i++) {
            threadPoolExecutor.execute(() -> {
//                synchronized (o){
                synchronized (this){
                    if (count[0] > 0) {
                        count[0] = count[0] -1;
                    } else {
                        System.out.println("已经没有库存了");
                    }
//                    System.out.println(count[0]);
                }
//                System.out.println("===>" + Thread.currentThread().getName());
            });
        }

        // 等待1秒，让所有线程执行完再返回结果
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return count[0];
    }


    public static void main(String[] args) {
        /* int size = 1000;
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
        System.out.println("result ===> " + result);*/


        /*AtomicInteger atomicInteger = new Demo1().subSync(100);
        try {
            Thread.sleep(10 * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(atomicInteger.get());*/

        int count = new Demo1().subSync1(100);
//        try {
//            TimeUnit.SECONDS.sleep(3L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(count);

    }

}
