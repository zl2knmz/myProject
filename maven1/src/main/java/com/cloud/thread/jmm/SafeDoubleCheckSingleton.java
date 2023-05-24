package com.cloud.thread.jmm;

/**
 * 双重校验的单例模式 (懒汉模式)
 *
 * @author zl
 * @date 2023/5/24 16:30
 */
public class SafeDoubleCheckSingleton {
    /**
     * 通过volatile声明，实现线程安全的延迟初始化
     */
    private volatile static SafeDoubleCheckSingleton singleton;

    /**
     * 私有化构造方法
     */
    private SafeDoubleCheckSingleton() {

    }

    /**
     * 双重锁设计
     */
    public static SafeDoubleCheckSingleton getInstance() {
        if (null == singleton) {
            // 1、多线程并发创建对象时，会通过加锁保证只有一个线程能创建对象
            synchronized (SafeDoubleCheckSingleton.class) {
                if (null == singleton) {
                    // 隐患：多线程环境下，由于重排序，该对象可能还未完成初始化就被其他线程读取
                    //解决隐患原理：利用volatile，禁止“初始化对象(2)”和“设置singleton指向内存空间(3)”的重排序
                    singleton = new SafeDoubleCheckSingleton();
                }
            }
        }
        // 2、对象创建完毕，执行getInstance()将不需要获取锁，直接返回创建对象
        return singleton;

    }

    public static void main(String[] args) {
        SafeDoubleCheckSingleton instance = getInstance();
        System.out.println(instance.hashCode());

        SafeDoubleCheckSingleton instance2 = getInstance();
        System.out.println(instance2.hashCode());

        SafeDoubleCheckSingleton instance3 = getInstance();
        System.out.println(instance3.hashCode());
    }
}
