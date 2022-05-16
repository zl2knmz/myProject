package com.cloud.offer.day3;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java.util.concurrent -> JUC
 *
 * AbstractQueuedSynchronizer -> AQS
 *
 * Node
 *
 * Sync
 *
 * ReentrantLock 可重入锁
 * CountDownLatch 计数器
 * ReentrantReadWriteLock 读写锁
 * Semaphore 信号灯
 *
 * @author zl
 * @date 2022/5/13 23:03
 */
public class AQSDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        // 带入一个银行办理业务的案例来模拟我们的AQS 如何进行线程的管理和通知唤醒机制

        // 3个线程模拟3个来银行网点受理窗口办理业务的顾问

        // A顾客就是第一个顾客，此时受理窗口没有任何人，A可以直接去办理
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-------A thread come in");
                // 办理20分钟
                try { TimeUnit.MINUTES.sleep(20L); } catch (InterruptedException e) { e.printStackTrace(); }
            } finally {
                lock.unlock();
            }
        }, "A").start();


        // 第二个顾客，第二个线程---》由于受理业务的窗口只有一个（只能一个线程持有锁）此时B只能等待
        // 进入候客区
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-------B thread come in");
                // 办理20分钟
                try { TimeUnit.MINUTES.sleep(20L); } catch (InterruptedException e) { e.printStackTrace(); }
            } finally {
                lock.unlock();
            }
        }, "B").start();

        // 第3个顾客，第3个线程---》由于受理业务的窗口只有一个（只能一个线程持有锁）此时C只能等待
        // 进入候客区
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-------B thread come in");
                // 办理20分钟
                try { TimeUnit.MINUTES.sleep(20L); } catch (InterruptedException e) { e.printStackTrace(); }
            } finally {
                lock.unlock();
            }
        }, "C").start();

    }
}
