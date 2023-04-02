package com.cloud.thread.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zl
 * @date 2023/4/2 22:21
 */
public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "abc";
        });

//        System.out.println(completableFuture.get());
//        System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));

        // join方法 无需抛出异常
//        System.out.println(completableFuture.join());

        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // getNow() 如果已完成，则返回结果值（或抛出任何遇到的异常），否则返回给定的值IfAbsent。
//        System.out.println(completableFuture.getNow("xxx"));

        // complete() 如果没计算完，将返回的 get()值为给定值。true	complete 否则 false	abc
        System.out.println(completableFuture.complete("complete") + "\t" + completableFuture.join());

    }
}
