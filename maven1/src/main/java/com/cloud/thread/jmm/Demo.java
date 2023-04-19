package com.cloud.thread.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * happens-before原则的威力 -> 包含可见性和有序性的约束
 * @author zl
 * @date 2023/4/19 23:21
 */
public class Demo {
    public static void main(String[] args) {
        AtomicInteger x = new AtomicInteger(0);
        AtomicInteger y = new AtomicInteger(0);
        new Thread(() -> {
            x.set(5);
            System.out.println(Thread.currentThread().getName() + "线程执行：x=" + x + " y=" + y);
        }, "t1").start();

        new Thread(() -> {
            y.set(x.get());
            System.out.println(Thread.currentThread().getName() + "线程执行：x=" + x + " y=" + y);
        }, "t2").start();

        System.out.println(Thread.currentThread().getName() + "线程执行：x=" + x + " y=" + y);
    }
}
