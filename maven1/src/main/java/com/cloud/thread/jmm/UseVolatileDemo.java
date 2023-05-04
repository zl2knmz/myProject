package com.cloud.thread.jmm;

/**
 * 使用：当读远多于写，结合使用内部锁和volatile 变量来减少同步的开销
 * 理由：利用volatile保证读取操作的可见性，利用synchronize保证复合操作的原子性
 *
 * @author zl
 * @date 2023/5/4 23:11
 */
public class UseVolatileDemo {
    private volatile int number;

    public int getNumber() {
        // 利用volatile保证读取操作的可见性
        return number;
    }

    public synchronized int increment() {
        // 利用synchronize保证复合操作的原子性
        return number++;
    }
}
