package com.cloud.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * 1、死锁是什么
 * 死锁是指两个或两个以上的线程在执行过程中，因争夺资源而造成的一种互相等待的现象，若无外力干涉那它们都将无法推进下去，
 * 如果系统资源充足，进程的资源请求都能够得到满足，死锁出现的可能性就很低，否则就会因争夺有限的资源而陷入死锁。
 * <p>
 * 2、产生死锁主要原因
 * 系统资源不足
 * 进程运行推进的顺序不合适
 * 资源分配不当
 * <p>
 * 3、死锁排查命令
 * jps -l // 查看当前程序进程编号 (jsp -> java版的 ps -ef)
 * jstack 11248 // jstack + 进程号 （查看堆栈信息）
 * jconsole // 图形化查看死锁
 *
 * @author zl
 * @date 2023/4/10 22:33
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();

        new Thread(() -> {
            synchronized (objectA) {
                System.out.println(Thread.currentThread().getName() + "\t------线程A 获取到锁A，希望获得B锁");
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectB) {
                    System.out.println(Thread.currentThread().getName() + "\t------线程A 获取到锁B");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectB) {
                System.out.println(Thread.currentThread().getName() + "\t------线程B 获取到锁B，希望获得A锁");
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectA) {
                    System.out.println(Thread.currentThread().getName() + "\t------线程B 获取到锁A");
                }
            }
        }, "B").start();

    }

}
