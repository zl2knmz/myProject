package com.cloud.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁又名递归锁
 * <p>
 * 是指在同一个线程在外层方法获取锁的时候，再进入该线程的内层方法会自动获取锁（前提，锁对象得是同一个对象），
 * 不会因为之前已经获取过还没释放而阻塞。
 * <p>
 * 如果是1个有synchronized 修饰的递归调用方法，程序第2次进入被自己阻塞了岂不是天大的笑话，出现了作茧自缚。
 * 所以Java中ReentrantLock和synchronized都是可重入锁，可重入锁的一个优点是可一定程度避免死锁。
 * <p>
 * 可：可以
 * 重：再次
 * 入：进入
 * 锁：同步锁
 * <p>
 * 进入什么：进入同步域（即同步代码块/方法或显示锁锁定的代码）
 * 一句话：一个线程中的多个流程可以获取同一把锁，持有这把同步锁可以再次进入。
 * 自己可以获取自己的内部锁
 * <p>
 * <p>
 * 可重入锁的种类：
 * 1、隐式锁（即synchronized关键字使用的锁）默认是可重入锁，同步块、同步方法都支持
 * 指的是可重复可递归调用的锁，在外层使用锁之后，在内层任然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
 * 简单的来说就是：在一个synchronized修饰的方法或代码块的内部调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的。
 * 2、Synchronized的重入的实现机理
 * 3、显示锁（即Lock）也有ReentrantLock这样的可重入锁。
 *
 * @author zl
 * @date 2023/4/9 15:36
 */
public class ReentrantLockDemo {

    public synchronized void m1() {
        // 指的是可重复可递归调用的锁，在外层使用锁之后，在内层任然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
        System.out.println(Thread.currentThread().getName() + "\t -----come in m1");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t -----end m1");
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t -----come in m2");
        m3();
    }

    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t -----come in m3");
    }

    public static void main(String[] args) {
//        reEntryM1();

        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        new Thread(() -> {
            reentrantLockDemo.m1();
        }, "t1").start();

    }

    private static void reEntryM1() {
        final Object object = new Object();
        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t --------外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t --------中层调用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t --------内层调用");
                    }
                }
            }
        }, "t1").start();
    }
}