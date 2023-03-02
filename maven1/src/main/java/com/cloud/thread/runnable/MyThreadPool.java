package com.cloud.thread.runnable;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用线程池创建线程
 *
 * @author zl
 * @date 2023/2/28 16:22
 */
public class MyThreadPool {

    private static final AtomicInteger COUNT = new AtomicInteger(0);

    /**
     * 比如一次性提交了100个任务，每个任务执行30秒，那么按照下面的这个线程池的参数，100个任务都是如何被执行的呢？
     * 1、首先线程池会创建5个线程去执行前5个任务
     * 2、从第6个任务开始来的都会放到任务队列中，即LinkedBlockingDeque中
     * 3、当任务队列的50个任务放满以后，那么这个时候还剩下45个任务（5个在执行 + 50个在队列中 = 55个）
     * 4、第56-60个任务来了以后会继续创建线程去执行，这里只能再创建5个线程，因为最大线程数是10（ 5 + 5 = 10）
     * 5、第60-100个会去执行拒绝策略，则会抛出40个异常
     * <p>
     * 线程数：计算密集型应用 N+1
     * IO密集型应用  2N （N是cpu数量）
     */
    public static ThreadPoolExecutor initThreadPool() {
        LinkedBlockingQueue queue = new LinkedBlockingQueue<>(50);
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
        ThreadPoolExecutor threadPoolExecutor = initThreadPool();
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
    }

}
