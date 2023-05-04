package com.cloud.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁：是指多个线程按照申请的顺序来获取锁，这里类似排队买票，先来的人先买后来的人在队尾排着，这是公平的
 * Lock lock = new ReentrantLock(true); // true表示公平锁，先来先得
 * <p>
 * 非公平锁：是指多个线程获取锁的顺序并不是按照申请锁的顺序，有可能后申请的比先申请的线程优先获取锁，在高并发环境下，
 * 有可能造成优先级翻转或者饥饿的状态（某个线程一直得不到锁）
 * Lock lock = new ReentrantLock(false); // false表示非公平锁，后来的也可能先获得锁
 * Lock lock = new ReentrantLock(); // 默认非公平锁
 * <p>
 * 为什么会有公平锁、非公平锁的设计？为什么默认非公平？
 * 1、恢复挂起的线程到真正锁的获取还是有时间差的，从开发人员来看这个时间微乎其微，但是从CPU的角度来看，这个时间差存在的还是很明显的。
 * 所以非公平锁能更充分的利用CPU的时间片，尽量减少CPU空闲状态时间。
 * <p>
 * 2、使用多线程很重要的考量点是线程切换的开销，当采用非公平锁时，当1个线程请求锁获取同步状态，然后释放同步状态，
 * 所以刚释放锁的线程在此刻再次获取同步状态的概率就变得非常大，所以就减少了线程的开销。
 *
 * @author zl
 * @date 2023/4/9 10:36
 */
public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "c").start();

    }
}

/**
 * 资源类，模拟3个售票员卖完50张票
 */
class Ticket {
    private int number = 50;
    private final AtomicInteger number1 = new AtomicInteger(50);

    /**
     * 默认非公平锁
     * new ReentrantLock() 非公平锁
     * new ReentrantLock(true) 公平锁
     */
    ReentrantLock lock = new ReentrantLock(true);

    public void sale() {
//        System.out.println(Thread.currentThread().getName() + " come in" );
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：" + (number--) + "\t 还剩下：" + number);
            }

            // juc 原子类
            if (number1.get() > 1) {
                System.out.println(Thread.currentThread().getName() + "卖出第=" + (number1.decrementAndGet()) + "\t 还剩下=" + (number1.get() - 1));
            }
        } finally {
            lock.unlock();
        }
    }
}
