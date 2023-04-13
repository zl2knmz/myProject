package com.cloud.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author zl
 * @date 2023/4/13 23:34
 */
public class InterruptDemo2 {
    public static void main(String[] args) {
        // 实例方法interrupt()仅仅是设置线程的中断状态为true，不会停止线程。
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println("---------: " + i);
            }
            System.out.println("t1线程调用interrupt()后的中断标识02：" + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        // false
        System.out.println("t1线程默认的中断标识：" + t1.isInterrupted());

        // 暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(2L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // true
        t1.interrupt();
        System.out.println("t1线程调用interrupt()后的中断标识01：" + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // false 中断不活动的线程不会产生任何影响，此时t1线程已经结束。
        System.out.println("t1线程调用interrupt()后的中断标识03：" + t1.isInterrupted());

    }

}
