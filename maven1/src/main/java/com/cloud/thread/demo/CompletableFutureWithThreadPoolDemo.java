package com.cloud.thread.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1、没有传入自定义线程池，都用默认线程池ForkJoinPool
 * 2、传入了一个自定义线程池
 *    如果执行第一个任务的时候，传入了一个自定义线程池：
 *        调用ThenRun方法 执行第二个任务时，则第二个任务和第一个任务公用一个线程池。
 *        调用ThenRunAsync方法(异步) 执行第二个任务时，则第一个任务使用自定义线程池，第二个任务使用ForkJoinPool线程池。
 * 3、备注
 *    有可能处理太快，系统优化切换原则，直接使用main线程处理
 *    其他如：thenAccept和thenAcceptAsync、thenApply、thenApplyAsync方法等，它们之间的区别也是同理。
 *
 * @author zl
 * @date 2023/4/3 22:23
 */
public class CompletableFutureWithThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(20L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "abcd";
            }, threadPool).thenRunAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(20L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            });

            System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }

    }
}
