package com.cloud.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * jdk11在线API文档
 * @https://www.runoob.com/manual/jdk11api/index.html
 *
 * LockSupport与线程中断
 * 1、内容介绍
 * <p>
 * 2、线程中断机制
 * 首先：一个线程不应该由其他线程来强制中断或停止，而是应该由线程自己自行停止，自己来决定自己的命运。
 * 所以，Thread.stop(),Thread.suspend(),Thread.resume()方法都已经被废弃了。
 * 其次：在Java中没有办法立即停止一个线程，然而停止线程却显得尤为重要，如取消一个耗时操作。
 * 因此，Java提供了一种用于停止线程的协商机制————中断，也即中断标识协商机制。
 * <p>
 * 中断只是一种协作协商机制，Java没有给中断增加任何语法，中断的过程完全需要程序员自己实现。
 * 若要中断一个线程，你需要手动调用该线程的interrupt方法，该方法也仅仅是将线程对象的中断标识设成true；
 * 接着你需要自己写代码不断的检测当前线程的标示位，如果为true，表示别的线程请求这个线程中断，
 * 此时究竟该做什么需要你自己写代码实现。
 * <p>
 * 每个线程对象中都有一个中断标识位，用于表示线程是否被中断；该标识位为true表示中断，为false表示未中断；
 * 通过调用线程对象的interrupt方法将该线程的标识位设为true；可以在别的线程中调用，也可以在自己的线程中调用。
 * <p>
 * 3、LockSupport是什么
 * <p>
 * 4、线程等待唤醒机制
 *
 * @author zl
 * @date 2023/4/11 23:54
 */
public class InterruptDemo {

    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
//        m1_volatile();

//        m2_atomicBoolean();

        m3_interrupt();
    }

    /**
     * void  interrupt() 中断此线程。
     *   实例方法interrupt()仅仅是设置线程的中断状态为true，发起一个协商而不会立刻停止线程。
     *
     * static boolean interrupted()  测试当前线程是否已被中断。
     *   静态方法Thread.interrupted() 判断线程是否被中断并清除当前中断状态。
     *   这个方法做了两件事：
     *   1、返回当前线程的中断状态，测试当前线程是否已被中断
     *   2、将当前线程的中断状态清零并重新设为false，清除线程的中断状态
     *   此方法有点不好理解，如果连续两次调用此方法，则第二次调用将返回false，因为连续调用两次的结果可能不一样
     *
     * boolean  isInterrupted()   测试此线程是否已被中断。
     *   实例方法，判断当前线程是否被中断（通过检查中断标志位）
     *
     * 具体的来说，当对一个线程调用interrupt()时：
     *     1、如果线程处于正常活动状态，那么会将该线程的中断标志设置为true，仅此而已。
     *       所以，interrupt() 并不能正真的中断线程，需要被调用的线程自己进行配合才行。
     *     2、如果线程处于被阻塞状态（例如处于sleep，wait，join等状态），在别的线程中调用当前线程对象的interrupt()方法，
     *        那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。
     *
     * 如何停止中断运行中的线程？
     *
     * 当前线程的中断标识为true，是不是线程就立刻停止？ 不是
     *
     * 静态方法Thread.interrupted()，谈谈你的理解？
     */
    private static void m3_interrupt() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 ----------hello isInterrupted api");
            }
        }, "t1");
        t1.start();

        System.out.println("----t1的默认中断标志位：" + t1.isInterrupted());
        try {
            TimeUnit.MILLISECONDS.sleep(20L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // t1向 t2发出协商，将t1的中断标志位设为true希望t1停下来
//        new Thread(() -> {
//            t1.interrupt();
//        }, "t2").start();

        System.out.println(Thread.currentThread().getName() + "-----线程");
        // 也可以t1自己设置
        t1.interrupt();
    }

    /**
     * AtomicBoolean 原子类 （线程安全）十八罗汉
     */
    private static void m2_atomicBoolean() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 ----------hello atomicBoolean");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    /**
     * volatile 可见性 boolean值
     */
    private static void m1_volatile() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 ----------hello volatile");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }


}
