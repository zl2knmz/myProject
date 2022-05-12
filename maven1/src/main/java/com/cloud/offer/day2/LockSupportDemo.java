package com.cloud.offer.day2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Java11中文手册
 * http://apiref.com/java11-zh
 *
 * @author zl
 * @date 2022/5/11 23:16
 */
public class LockSupportDemo {
    static Object objectLock = new Object();

    public static void m1() {
        new Thread(() -> {
            // 暂停几秒钟线程
//            try {
//                TimeUnit.SECONDS.sleep(3L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t" + "come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒");
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t" + "通知");
            }
        }, "B").start();
    }

    public static void m2() {


        Thread a = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "\t" + "come in " + System.currentTimeMillis());
            // 被阻塞...等待通知带放行，他要通过 需要许可证
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒  "  + System.currentTimeMillis());
        }, "a");
        a.start();

        // 暂停几秒钟线程
//        try {
//            TimeUnit.SECONDS.sleep(3L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Thread b = new Thread(() -> {
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t" + "通知");
        }, "b");
        b.start();
    }

    public static void main(String[] args) {
//        m1();
        m2();

    }
}
