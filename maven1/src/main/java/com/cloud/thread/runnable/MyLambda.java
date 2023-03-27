package com.cloud.thread.runnable;

/**
 * 使用lambda创建线程
 *
 * @author zl
 * @date 2023/2/28 16:15
 */
public class MyLambda {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("run thread lambda");
        }, "thread");
        thread.start();
    }
}
