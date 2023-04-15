package com.cloud.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockSupport是用来创建锁和其他同步类的基本线程阻塞原语。
 * <p>
 * LockSupport中的park()和unpark(线程)的作用分部是阻塞线程和解除阻塞线程。
 * <p>
 * 线程等待和唤醒的方法：
 * 方式1：使用Object中的wait()方法让线程等待，使用Object中的notify()方法唤醒线程
 *       使用场景：包在synchronized同步代码块之间，否则抛出异常IllegalMonitorStateException
 *                先notify再wait程序无法执行，无法唤醒
 *       总结：wait和notify方法必须要在同步块或者方法里面，而且成对出现使用，先wait后notify才OK
 *
 * 方式2：使用JUC包中Condition的 await()方法让线程等待，使用 signal()方法唤醒线程
 *       使用场景：包在lock、unlock对里面，否则抛出异常IllegalMonitorStateException
 *       总结：Condition中线程等待和唤醒的方法，需要先获取锁。先await()后signal()才行，否则线程无法被唤醒
 *
 * 上述两个对象 Object 和 Condition 使用的限制条件：
 *   1、线程先要获得并持有锁，必须在锁块(synchronized或lock)中
 *   2、必须要先等待后唤醒，线程才能够被唤醒
 *
 * 方式3：LockSupport类可以阻塞当前线程以及唤醒指定被阻塞的线程
 *
 *
 * @author zl
 * @date 2023/4/15 18:24
 */
public class LockSupportDemo {
    public static void main(String[] args) {
//        sync_wait_notify();

//        lock_await_signal();

        LockSupport.park();

    }

    private static void lock_await_signal() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t----被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t----发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    private static void sync_wait_notify() {
        Object objectLock = new Object();
        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t----被唤醒");
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t----发出通知");
            }
        }, "t2").start();
    }

}
