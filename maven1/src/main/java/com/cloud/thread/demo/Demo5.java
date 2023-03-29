package com.cloud.thread.demo;

import java.util.concurrent.*;

/**
 * @author zl
 * @date 2023/3/28 22:41
 */
public class Demo5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 3个任务，目前只有一个线程main来处理，请问耗时多少？
        long startTime = System.currentTimeMillis();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "task over";
        });
        fixedThreadPool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "task2 over";
        });
        fixedThreadPool.submit(futureTask2);

        // 获取返回值后 894毫秒
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        // main线程
        try {
            TimeUnit.MILLISECONDS.sleep(300L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        // 没有get 378毫秒
        System.out.println(endTime - startTime + "毫秒");
        System.out.println(Thread.currentThread().getName() + "\t ---------end");

        // 线程池关闭
        fixedThreadPool.shutdown();

    }

    public static void main2(String[] args) {
        // 3个任务，目前只有一个线程main来处理，请问耗时多少？
        long startTime = System.currentTimeMillis();
        // 暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(300L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(300L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();

        // 1118毫秒
        System.out.println(endTime - startTime + "毫秒");
        System.out.println(Thread.currentThread().getName() + "\t ---------end");
    }
}
