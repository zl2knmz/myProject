package com.cloud.thread.jmm;

import java.util.concurrent.TimeUnit;

/**
 * volatile修饰的变量有2大特点：1-可见性，2-有序性（禁止指令重排）
 * <p>
 * 通过内存屏障实现：
 * 1、volatile修饰的变量，修改后立刻更新到主内存。
 * 2、读volatile变量，将线程本地内存副本值设置无效，去主内存中读最新共享变量。
 * <p>
 * 内存屏障类型：
 * 1、读屏障（Load Barrier）
 * 在读指令之前插入读屏障，让工作内存或CPU高速缓存当中的缓存数据失效，重新回到主内存中获取最新数据。
 * 2、写屏障（Store Barrier）
 * 在写指令之后插入写屏障，强制把写缓冲区的数据刷回到主内存中
 *
 * @author zl
 * @date 2023/4/22 23:47
 */
public class VolatileDemo {

    /**
     * 不加volatile，t1始终不停止，没有读到最新的flag值
     */
    static volatile boolean flag = true;

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + "\t 线程结束");
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t 修改flag值为false");

    }


}
