package com.cloud.thread.demo;

import com.cloud.thread.runnable.MyThreadPool;

import java.util.concurrent.*;

/**
 * CompletableFuture
 * CompletableFuture 增强版FutureTask
 * 不阻塞程序也不需要轮询获取返回结果
 * whenComplete 计算完回调程序返回结果
 * @author zl
 * @date 2023/3/30 21:52
 */
public class Demo7 {
    public static void main1(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        /*CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//        }, MyThreadPool.initThreadPool());
        }, executorService);
        System.out.println(voidCompletableFuture.get());*/


        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello supplyAsync";
//        }, MyThreadPool.initThreadPool());
        }, executorService);
//        });
        System.out.println(objectCompletableFuture.get());

        executorService.shutdown();


    }

    /**
     * 替换FutureTask
     */
    public static void main2(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("---------1秒钟后出结果: " + result);
            return result;

        });

        System.out.println(Thread.currentThread().getName() + " 线程先去忙其他的事了");

        System.out.println(integerCompletableFuture.get());
    }

    /**
     * CompletableFuture 增强版FutureTask
     * 不阻塞程序也不需要轮询获取返回结果
     * whenComplete 计算完回调程序返回结果
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "---------come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("---------1秒钟后出结果: " + result);
                // 抛异常
                if (result > 5) {
                    int i = 10 / 0;
                }
                return result;
                // 默认线程池是个守护线程（setDaemon(true)），main线程结束后自动结束了
//            }).whenComplete((v, e) -> {
                // 自定义线程池
            }, executorService).whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("--------计算完成，更新系统UpdateValue：" + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName() + " 线程先去忙其他的事了");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭：暂停3秒钟
        /*try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

    }

}
