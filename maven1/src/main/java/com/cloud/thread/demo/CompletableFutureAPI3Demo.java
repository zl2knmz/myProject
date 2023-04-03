package com.cloud.thread.demo;

import java.util.concurrent.CompletableFuture;

/**
 * thenRun 方法：任务A执行完执行任务B，并且B不需要A的结果
 * thenAccept 方法：任务A执行完执行任务B，B需要A的结果，但是任务B无返回值
 * thenApply 方法：任务A执行完执行任务B，B需要A的结果，同时任务B有返回值
 *
 * @author zl
 * @date 2023/4/2 23:09
 */
public class CompletableFutureAPI3Demo {
    public static void main(String[] args) {
        /*CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f -> {
            return f + 2;
        }).thenApply(f -> {
            return f + 3;
        }).thenAccept(System.out::println);*/

        // thenRun 返回值：null
        System.out.println(CompletableFuture.supplyAsync(() -> "ResultA").thenRun(() -> {}).join());

        // thenAccept 返回值：ResultA null
        System.out.println(CompletableFuture.supplyAsync(() -> "ResultA").thenAccept(System.out::println).join());

        // thenRun 返回值：ResultA ResultB
        System.out.println(CompletableFuture.supplyAsync(() -> "ResultA").thenApply((r) -> r + " ResultB").join());

    }
}
