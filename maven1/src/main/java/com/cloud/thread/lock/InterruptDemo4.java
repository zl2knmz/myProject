package com.cloud.thread.lock;

/**
 * 线程中断相关的方法：
 * public void interrupt()
 * 实例方法，它通知目标线程中断，也仅仅是设置目标线程的中断标志位为true。
 *
 * public boolean isInterrupted()
 * 实例方法，它判断当前线程是否被中断（通过检查中断标志位）并获取中断标志。
 *
 * public static boolean interrupted()
 * Thread类的静态方法，返回当前线程的中断状态真实值（boolean类型）后，会将当前线程的中断状态设为false，
 * 此方法调用之后会清除当前线程的中断标志位状态（将中断标志设置为false），返回当前值并清零置false
 *
 * @author zl
 * @date 2023/4/14 17:18
 */
public class InterruptDemo4 {
    public static void main(String[] args) {
        // 测试当前线程是否被中断（检查中断标志），返回一个boolean并清除中断状态
        // 第二次再调用时中断状态已经被清除，将返回一个false

        // false
        System.out.println(Thread.currentThread().getName() + "线程中断状态：" + Thread.interrupted());
        // false
        System.out.println(Thread.currentThread().getName() + "线程中断状态：" + Thread.interrupted());

        System.out.println("----1");
        // 中断标志位设置成true
        Thread.currentThread().interrupt();
        System.out.println("----2");

        // true
        System.out.println(Thread.currentThread().getName() + "线程中断状态：" + Thread.interrupted());
        // false
        System.out.println(Thread.currentThread().getName() + "线程中断状态：" + Thread.interrupted());


        // 静态方法：返回当前中断状态，并清除中断状态。
        Thread.interrupted();
        // 实例方法：返回当前中断状态
        Thread.currentThread().isInterrupted();
        // 底层调用的都是 private native boolean isInterrupted(boolean ClearInterrupted); 传参不一样
    }
}
