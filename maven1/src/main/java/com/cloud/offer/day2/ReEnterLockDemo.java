package com.cloud.offer.day2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁：可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，
 * 在一个synchronized修饰的方法或代码块的内部
 * 调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 *
 * @author zl
 * @date 2022/5/10 20:50
 */
public class ReEnterLockDemo {
    static Object objectLockA = new Object();
    static Lock lock = new ReentrantLock();

    /**
     * cd D
     * cd \Program Files\development\GitHubProjects\myProject\maven1\target\classes\com\cloud\offer\day2
     * 反编译
     * javap -c ReEnterLockDemo.class
     */
    public static void m1() {
        new Thread(() -> {
            synchronized (objectLockA) {
                System.out.println(Thread.currentThread().getName() + "\t" + "-----------外层调用");
                synchronized (objectLockA) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "-----------中层调用");
                    synchronized (objectLockA) {
                        System.out.println(Thread.currentThread().getName() + "\t" + "-----------内层调用");
                    }
                }
            }
        }, "t1").start();
    }

    public synchronized void m2() {
        System.out.println("==========外");
        m3();
    }

    public synchronized void m3() {
        System.out.println("==========中");
        m4();
    }

    public synchronized void m4() {
        System.out.println("==========内");
    }


    public static void m5() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("=====外层");
                lock.lock();
                try {
                    System.out.println("=====内层");
                } finally {
                    lock.unlock();
                }
            } finally {
                // 由于加锁次数和释放次数不一样，第二个线程始终无法获取到锁，导致一直在等待。
                lock.unlock(); // 正常情况下，加锁几次就要解锁几次
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "=====调用开始");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    public static void main(String[] args) {
//        m1();
//        new ReEnterLockDemo().m2();
        m5();
    }


}
