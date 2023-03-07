package com.cloud.thread.demo;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zl
 * @date 2023/3/2 16:48
 */
public class Demo1 {
    private  final AtomicInteger COUNT = new AtomicInteger(0);

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

    public List<Integer> sumSync() {
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
            sum = sum + i;
        }
        System.out.println("sum ===> " + sum);

        List<Integer> sumList = new ArrayList<>();
//        List<List<Integer>> parts = SplitListUtils.split(list, 100);
        List<List<Integer>> parts = Lists.partition(list, 5);

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
        List<Integer> list = new Demo1().sumSync();
        System.out.println("======list======="+list.size());
        int result = 0;
        for (Integer integer : list) {
            result = result + integer;
        }
        System.out.println("result ===> " + result);
    }
}
