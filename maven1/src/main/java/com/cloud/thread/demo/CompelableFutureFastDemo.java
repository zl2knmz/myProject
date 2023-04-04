package com.cloud.thread.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * applyToEither() 哪个线程执行快，就返回哪个
 *
 * @author zl
 * @date 2023/4/3 22:59
 */
public class CompelableFutureFastDemo {
    public static void main(String[] args) {
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in");
            try {
                TimeUnit.SECONDS.sleep(4L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playA";
        });

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in");
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playB";
        });

        // applyToEither() 谁快谁就是赢家
        CompletableFuture<String> result = playA.applyToEither(playB, f -> {
            return f + " is winner";
        });
        System.out.println(Thread.currentThread().getName() + "\t----: " + result.join());
    }
}
