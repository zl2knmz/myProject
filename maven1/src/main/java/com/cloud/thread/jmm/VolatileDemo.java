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
 * Java内存模型中定义的8种每个线程自己的工作内存与主物理内存之间的原子操作
 *  read(读取) -> load(加载) -> use(使用) -> assign(赋值) -> store(存储) -> write(写入) -> lock(锁定) -> unlock(解锁)
 *
 * read：作用于主内存，将变量的值从主内存传输到工作内存，主内存到工作内存
 * load：作用于工作内存，将read从主内存传输的变量值放入工作内存变量副本中，即数据加载
 * use：作用于工作内存，将工作内存变量副本的值传递给执行引擎，每当JVM遇到需要改变量的字节码指令时会执行该操作
 * assign：作用于工作内存，将从执行引擎接收到的值赋值给工作内存变量，每当JVM遇到一个给变量赋值字节码指令时会执行该操作
 * store：作用于工作内存，将赋值完毕的工作变量的值写回给主内存
 * write：作用于主内存，将store传输过来的变量赋值给主内存中的变量
 *
 * 由于上述6条只能保证单条指令的原子性，针对多条指令的组合性原子保证，没有大面积加锁，所以，JVM提供了另外两个原子指令：
 * lock：作用于主内存，将一个变量标记为一个线程独占的状态，只是写时候加锁，就只是锁了写变量的过程。
 * unlock：作用于主内存，把一个处于锁定状态的变量释放，然后才能被其他线程占用
 *
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
