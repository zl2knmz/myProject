package com.cloud.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockSupport是用来创建锁和其他同步类的基本线程阻塞原语。
 * LockSupport类使用了一种名为Permit（许可）的概念来做到阻塞和唤醒的功能，每个线程都有一个许可（permit），
 * 但与Semaphore不同的是，许可的累加上限是1。
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
 *       正常+无锁块要求
 *       之前错误的先唤醒后等待，LockSupport照样支持
 *       成双成对要牢记
 *
 * 重点说明（重要）
 * LockSupport是用来创建锁和其他同步类的基本线程阻塞原语。
 * LockSupport是一个线程阻塞工具类，所有的方法都是静态方法，可以让线程在任意位置阻塞，阻塞之后也有对应的唤醒方法，
 * 归根结底，LockSupport调用的Unsafe中的native代码。
 *
 * LockSupport提供park()和unpark()方法实现阻塞线程和解除线程阻塞的过程
 * LockSupport和每个使用它的线程都有一个许可(permit)关联。
 * 每个线程都有一个相关的permit，permit最多只有一个，重复调用unpark也不会积累凭证。
 *
 * 形象的理解
 * 线程阻塞需要消耗凭证(permit)，这个凭证最多只有1个。
 * 当调用park方法时
 *    *如果有凭证，则会直接消耗掉这个凭证然后正常退出；
 *    *如果无凭证，就必须阻塞等待凭证可用；
 *
 * 而unpark则相反，它会增加一个凭证，但凭证最多只能有1个，累加无效。
 *
 * 面试题：
 * 1、为什么可以突破wait/notify的原有调用顺序？
 *   因为unpark获得了一个凭证，之后再调用park方法，就可以名正言顺的凭证消费，故不会阻塞。
 *   先发放了凭证，后续可以畅通无阻。
 *
 * 2、为什么唤醒两次后阻塞两次，但最终结果还是会阻塞线程？
 *   因为凭证的数量最多为1，连续调用两次unpark和调用一次unpark效果一样，只会增加一个凭证；
 *   而调用两次park却需要消费两个凭证，证不够，不能放行。
 *
 *
 * @author zl
 * @date 2023/4/15 18:24
 */
public class LockSupportDemo {
    public static void main(String[] args) {
//        sync_wait_notify();

//        lock_await_signal();

        lockSupport_park_unpark();
    }

    /**
     * 底层Unsafe.java源码 park()方法
     *
     * Block current thread, returning when a balancing
     * <tt>unpark</tt> occurs, or a balancing <tt>unpark</tt> has
     * already occurred, or the thread is interrupted, or, if not
     * absolute and time is not zero, the given time nanoseconds have
     * elapsed, or if absolute, the given deadline in milliseconds
     * since Epoch has passed, or spuriously (i.e., returning for no
     * "reason"). Note: This operation is in the Unsafe class only
     * because <tt>unpark</tt> is, so it would be strange to place it
     * elsewhere.
     */
//        public native void park(boolean isAbsolute, long time);
    /*
     *  LockSupport.java类 park方法
     *     public static void park() {
     *         UNSAFE.park(false, 0L);
     *     }
     */

    // permit许可证默认没有不能放行，所以一开始调park()方法当前线程就会阻塞。
//        LockSupport.park();

    /**
     * 底层Unsafe.java源码 unpark()方法
     *
     * Unblock the given thread blocked on <tt>park</tt>, or, if it is
     * not blocked, cause the subsequent call to <tt>park</tt> not to
     * block.  Note: this operation is "unsafe" solely because the
     * caller must somehow ensure that the thread has not been
     * destroyed. Nothing special is usually required to ensure this
     * when called from Java (in which there will ordinarily be a live
     * reference to the thread) but this is not nearly-automatically
     * so when calling from native code.
     * @param thread the thread to unpark.
     *
     */
//        public native void unpark(Object thread);
    /* LockSupport.java类 unpark方法
     *    public static void unpark(Thread thread) {
     *         if (thread != null)
     *             UNSAFE.unpark(thread);
     *     }
     */
//        LockSupport.unpark(Thread.currentThread());
    private static void lockSupport_park_unpark() {
        Thread t1 = new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "\t----come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t----被阻塞");

            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t----被阻塞");


            System.out.println(Thread.currentThread().getName() + "\t----被唤醒");
        }, "t1");
        t1.start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t----发出通知");
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t----发出通知");
        }, "t2").start();


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
