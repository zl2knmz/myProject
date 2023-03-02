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
    private static final AtomicInteger COUNT = new AtomicInteger(0);
    private static Boolean flag = true;
    private static int result = 0;

    public static ThreadPoolExecutor initThreadPool() {
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


    public static void main(String[] args) {
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
            sum = sum + i;
        }

        List<Integer> sumList = new ArrayList<>();
//        List<List<Integer>> parts = SplitListUtils.split(list, 100);
        List<List<Integer>> parts = Lists.partition(list, 50);
        System.out.println("parts size = " + parts.size());

        ThreadPoolExecutor threadPoolExecutor = initThreadPool();
        for (List<Integer> part : parts) {
            threadPoolExecutor.execute(() -> {
                int b = 0;
                for (Integer integer : part) {

//                        if (flag) {
//                            flag = false;
//                            synchronized (Demo1.class) {
//                                result = result + integer;
//                            }
//                        }
//                        flag = true;


                    b = b + integer;
                    System.out.println("---------------b=" + b);
                }
                sumList.add(b);

                System.out.println("===>" + Thread.currentThread().getName() + "-" + b);
            });
        }

        int c = 0;
        for (Integer integer : sumList) {
            System.out.println("============="+integer);
            c = c + integer;
        }
        result = c;
        System.out.println("sum ===> " + sum);
        System.out.println("result ===> " + result);

    }
}
