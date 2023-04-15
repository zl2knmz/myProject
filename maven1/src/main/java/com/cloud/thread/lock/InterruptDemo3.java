package com.cloud.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * 中断只是一种协商机制，修改中断标识位仅此而已，不是立刻stop打断。
 * 当对一个线程调用interrupt()时：
 *     如果线程处于被阻塞状态（例如处于sleep，wait，join等状态），在别的线程中调用当前线程对象的interrupt()方法，
 *     那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。
 *
 * @author zl
 * @date 2023/4/14 16:25
 */
public class InterruptDemo3 {
    public static void main(String[] args) {
        /**
         * 1、中断标志位，默认false
         * 2、t2 ---> t1发出了中断协商，t2调用t1.interrupt()，中断标志位true
         * 3、中断标志位true，正常情况，程序停止。
         * 4、中断标志位true，异常情况，InterruptedException，将会把中断状态清除，并且抛出InterruptedException异常，
         *    中断标志位变成false，导致无限循环。
         *
         * 5、在catch块中，需要再次给中断标志位设置为true，2次调用interrupt() 停止程序才OK
         *
         * 总结：
         *     sleep方法抛出InterruptedException后，中断标识也被清空置为false，我们在catch中没有调用interrupt()方法，
         *     再次将中断标识设置为true，这就导致无限循环了。
         */
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "线程 中断标志位：" + Thread.currentThread().isInterrupted() + " 程序停止");
                    break;
                }

                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    // 为什么要在异常处，再调用一次？？
//                    Thread.currentThread().interrupt(); // 没有它，程序不会停止，中断不打断，看看sleep方法的源码
                    e.printStackTrace();
                }
                System.out.println("---------hello InterruptDemo3");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // t2线程调用 t1线程interrupt()方法，中断t1线程
        new Thread(() -> t1.interrupt(), "t2").start();
    }
}


