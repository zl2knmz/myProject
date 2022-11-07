package com.cloud.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程池的使用
 *
 * @author zl
 * @date 2022/5/14 22:09
 */
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        testThreadPool();
    }

    public static void testThreadPool() {
        /**
         * 比如一次性提交了100个任务，每个任务执行30秒，那么按照下面的这个线程池的参数，100个任务都是如何被执行的呢？
         * 1、首先线程池会创建5个线程去执行前5个任务
         * 2、从第6个任务开始来的都会放到任务队列中，即LinkedBlockingDeque中
         * 3、当任务队列的50个任务放满以后，那么这个时候还剩下45个任务（5个在执行 + 50个在队列中 = 55个）
         * 4、第56-60个任务来了以后会继续创建线程去执行，这里只能再创建5个线程，因为最大线程数是10（ 5 + 5 = 10）
         * 5、第60-100个会去执行拒绝策略，则会抛出40个异常
         *
         * 线程数：计算密集型应用 N+1
         *         IO密集型应用  2N （N是cpu数量）
         *
         * */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(50),
                new MyThreadFactory("my_test_thread"),
                new MyRejectedExecutionHandler());

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println("===>" + Thread.currentThread().getName() + "-run");
                try {
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    System.out.println("异常线程" + Thread.currentThread().getName());
                }
            });
        }
        // 获取任务队列中的任务数
        System.out.println("LinkedBlockingDeque size:" + threadPoolExecutor.getQueue().size());
    }

    public static final AtomicInteger count = new AtomicInteger(0);

    public static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 模拟异常抛出，这里采用计数方式
            System.out.println("执行拒绝策略" + count.incrementAndGet());
        }
    }

    public static class MyThreadFactory implements ThreadFactory {
        private final String threadName;
        private final AtomicInteger atomicInteger;

        public MyThreadFactory(String threadName) {
            this.threadName = threadName;
            atomicInteger = new AtomicInteger(0);
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, threadName + "_" + atomicInteger.incrementAndGet());
        }
    }

}
