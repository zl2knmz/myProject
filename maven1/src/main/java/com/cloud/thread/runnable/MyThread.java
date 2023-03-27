package com.cloud.thread.runnable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 继承Thread创建线程
 *
 * @author zl
 * @date 2023/2/28 16:13
 */
@NoArgsConstructor
//@AllArgsConstructor
public class MyThread extends Thread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread("thread");
        myThread.start();
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("run my thread");
        System.out.println(Thread.currentThread().getName());
    }
}
