package com.cloud.offer.day2;

import java.util.concurrent.TimeUnit;

/**
 * Java11中文手册
 * http://apiref.com/java11-zh
 *
 * @author zl
 * @date 2022/5/11 23:16
 */
public class LockSupportDemo {
    static Object objectLock = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            // 暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
}
