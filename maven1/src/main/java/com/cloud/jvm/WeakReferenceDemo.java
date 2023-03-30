package com.cloud.jvm;

import java.lang.ref.WeakReference;

/**
 * 弱引用回收测试
 *
 * 当有强引用指向value内存区域时，即使进行gc，弱引用也不会被释放，对象不回被回收。
 *
 * 当无强引用指向value内存区域是，此时进行gc，弱引用会被释放，对象将会执行回收流程。
 *
 * 下面我们来简单地介绍下引用队列的概念。实际上，WeakReference类有两个构造函数：
 * 创建一个指向给定对象的弱引用``WeakReference(T referent)
 * 创建一个指向给定对象并且登记到给定引用队列的弱引用``WeakReference(T referent, ReferenceQueue<? ``super` `T> q)
 *
 * @author zl
 * @date 2022/10/31 16:36
 */
public class WeakReferenceDemo {

    public static WeakReference<String> weakReference1;

    public static void main(String[] args) {

        test1();
        //可以输出hello值，此时两个弱引用扔持有对象，而且未进行gc
        System.out.println("未进行gc时，只有弱引用指向value内存区域：" + weakReference1.get());

        //此时已无强引用执行"value"所在内存区域，gc时会回收弱引用
        System.gc();

        //此时输出都为nuill
        System.out.println("进行gc时，只有弱引用指向value内存区域：" + weakReference1.get());

    }

    public static void test1() {
        String hello = new String("value");

        weakReference1 = new WeakReference<>(hello);

        System.gc();
        //此时gc不会回收弱引用，因为字符串"value"仍然被hello对象强引用
        System.out.println("进行gc时，强引用与弱引用同时指向value内存区域：" + weakReference1.get());

    }
}
