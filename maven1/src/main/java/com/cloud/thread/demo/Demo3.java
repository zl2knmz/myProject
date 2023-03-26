package com.cloud.thread.demo;

import java.util.concurrent.TimeUnit;

/**
 * 用户线程之间互不干扰，守护线程随着用户线程结束同JVM一同结束工作
 *
 * @author zl
 * @date 2023/3/26 23:33
 */
public class Demo3 {
    public static void main(String[] args) {
        // 如果用户线程全部结束意味着程序需要完成的业务操作已经结束了，守护线程随着JVM一同结束工作
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 开始运行，" +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (true) {
            }
        }, "t1");
        // 守护线程：必须在start()之前设置，否则报java.lang.IllegalThreadStateException异常
        t1.setDaemon(true);
        t1.start();
        // This method must be invoked before the thread is started
//        t1.setDaemon(true);

        // 暂停几秒
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t ----------end 主线程");
    }
}
