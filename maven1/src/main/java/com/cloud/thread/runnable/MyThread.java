package com.cloud.thread.runnable;

/**
 * 继承Thread创建线程
 *
 * @author zl
 * @date 2023/2/28 16:13
 */
public class MyThread extends Thread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    @Override
    public void run() {
        System.out.println("run my thread");
    }
}
