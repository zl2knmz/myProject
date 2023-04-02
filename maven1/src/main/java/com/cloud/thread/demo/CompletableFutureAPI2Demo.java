package com.cloud.thread.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * thenApply方法 计算结果存在依赖关系，这两个线程串行化,有异常不走下一步
 * handle方法 计算结果存在依赖关系，这两个线程串行化,有异常可以往下一步走，根据异常参数可以进一步处理
 * @author zl
 * @date 2023/4/2 22:45
 */
public class CompletableFutureAPI2Demo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("111");
            return 1;
        }, threadPool).thenApply(f -> {
            System.out.println("222");
            return f + 2;
//        }).thenApply(f -> { // thenApply方法 计算结果存在依赖关系，这两个线程串行化,有异常不走下一步
        }).handle((f, e) -> { // handle方法 计算结果存在依赖关系，这两个线程串行化,有异常可以往下一步走，根据异常参数可以进一步处理
            int i = 10 / 0;
            System.out.println("333");
            return f + 3;
        }).thenApply((f) -> {
//        }).handle((f, e) -> {
            System.out.println("444");
            return f + 4;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("------计算结果" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "----主线程忙其他任务了");

        // 关闭线程池
        threadPool.shutdown();

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池ForkJoinPool(守护线程)会立刻关闭
        // 1、暂停3秒钟，等待默认的守护线程执行完结果。 2、自定义线程池（用户线程）
//        try {
//            TimeUnit.SECONDS.sleep(3L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
