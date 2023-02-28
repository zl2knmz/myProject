package com.cloud.thread.runnable;

/**
 * 实现Runnable接口创建线程
 *
 * @author zl
 * @date 2019/7/3 11:57
 */
public class MyRunnable implements Runnable {
    volatile boolean stop;

    void tellToStop() {
        stop = true;
    }

    @Override
    public void run() {
        System.out.println("进入不可停止区域 1。。。");
//        doingLongTime(5);
        System.out.println("退出不可停止区域 1。。。");
        System.out.println("检测标志stop = " + stop);
        if (stop) {
            System.out.println("停止执行");
            return;
        }
        System.out.println("进入不可停止区域 2。。。");
//        doingLongTime(5);
        System.out.println("退出不可停止区域 2。。。");
    }

    static void stopByFlag() {
        MyRunnable ar = new MyRunnable();
        new Thread(ar).start();
        ar.tellToStop();
    }

    public static void main(String[] args) {
        stopByFlag();
    }
}
