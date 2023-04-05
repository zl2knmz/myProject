package com.cloud.thread.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 两个CompletionStage任务都完成后，最终能把两个任务的结果一起交给 thenCombine() 来处理
 * 先完成的先等着，等待其它分支任务
 *
 * @author zl
 * @date 2023/4/4 23:36
 */
public class CompletableFutureCombineDemo {
    public static void main1(String[] args) {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t-----启动1");
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t-----启动2");
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });

        CompletableFuture<Integer> result = completableFuture1.thenCombine(completableFuture2, (x, y) -> {
            System.out.println("---------开始合并两个结果");
            return x + y;
        });

        System.out.println(result.join());
    }

    public static void main2(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> thenCombineResult = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t-----come in 1");
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t-----come in 2");
            return 20;
        }), (x, y) -> {
            System.out.println(Thread.currentThread().getName() + "\t-----come in 3");
            return x + y;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t-----come in 4");
            return 30;
        }), (a, b) -> {
            System.out.println(Thread.currentThread().getName() + "\t-----come in 5");
            return a + b;
        });

        System.out.println("-------主线程结束，END");
        System.out.println(thenCombineResult.get());
    }

    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t------come in 1");
            return 10;
        });

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t------come in 2");
            return 20;
        });

        // thenCombine
        CompletableFuture<Integer> completableFuture3 = completableFuture1.thenCombine(completableFuture2, (x, y) -> {
            System.out.println(Thread.currentThread().getName() + "\t------come in 3");
            return x + y;
        });

        CompletableFuture<Integer> completableFuture4 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t------come in 4");
            return 30;
        });

        CompletableFuture<Integer> completableFuture5 = completableFuture3.thenCombine(completableFuture4, (x, y) -> {
            System.out.println(Thread.currentThread().getName() + "\t------come in 5");
            return x + y;
        });

        System.out.println("-------主线程结束，END");
        System.out.println(completableFuture5.join());
    }
}
