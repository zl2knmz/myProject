package com.cloud.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author zl
 * @date 2022/5/14 22:09
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 线程池创建单线程
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        /**
         * 线程池7个参数
         *@param corePoolSize 核心线程数 the number of threads to keep in the pool, even
         *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
         * @param maximumPoolSize 最大线程数 the maximum number of threads to allow in the
         *        pool
         * @param keepAliveTime 核心线程存活时间 when the number of threads is greater than
         *        the core, this is the maximum time that excess idle threads
         *        will wait for new tasks before terminating.
         * @param unit 存活时间 单位 the time unit for the {@code keepAliveTime} argument
         * @param workQueue 阻塞队列 the queue to use for holding tasks before they are
         *        executed.  This queue will hold only the {@code Runnable}
         *        tasks submitted by the {@code execute} method.
         * @param threadFactory 线程工厂-取名 the factory to use when the executor
         *        creates a new thread
         * @param handler 拒绝策略4种 the handler to use when execution is blocked
         *        because the thread bounds and queue capacities are reached
         */
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.shutdown();
    }

    @Test
    public void test() {
        // 线程池创建线程
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        ExecutorService singleThreadPool = new ThreadPoolExecutor(2,
                10,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(new Thread1());
        singleThreadPool.execute(new Thread2());
        singleThreadPool.execute(new Thread3());
        singleThreadPool.execute(new Thread4());
        singleThreadPool.execute(new Thread5());
        singleThreadPool.execute(new Thread6());

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            singleThreadPool.shutdown();
        }

    }


    static class Thread1 implements Runnable  {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1........." + Thread.currentThread().getName());
        }
    }

    static class Thread2 implements Runnable  {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2........." + Thread.currentThread().getName());
        }
    }

    static class Thread3 implements Runnable  {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程3........." + Thread.currentThread().getName());
        }
    }

    static class Thread4 implements Runnable  {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程4........." + Thread.currentThread().getName());
        }
    }

    static class Thread5 implements Runnable  {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程5........." + Thread.currentThread().getName());
        }
    }

    static class Thread6 implements Runnable  {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程6........." + Thread.currentThread().getName());
        }
    }

}
