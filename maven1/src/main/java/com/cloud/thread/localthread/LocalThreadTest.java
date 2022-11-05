package com.cloud.thread.localthread;

/**
 * ThreadLocal类测试
 *
 * @author zl
 * @date 2022/10/31 11:52
 */
public class LocalThreadTest {
    private static ThreadLocal<Integer> localInt = new ThreadLocal<>();

    // ThreadLocal提供的静态方法withInitial来初始化一个ThreadLocal。
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

//    public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
//        return new SuppliedThreadLocal<>(supplier);
//    }

    public static int setAndGet(int num) {
        localInt.set(num);
        return localInt.get();
    }

    public static void remove() {
        localInt.remove();
    }

    public static void main(String[] args) {
        int i = LocalThreadTest.setAndGet(0);
        System.out.println(Thread.currentThread().getName() + ",localInt=" + i);
        LocalThreadTest.remove();
        System.out.println(Thread.currentThread().getName() + ",localInt=" + i);

        System.out.println("------1------");
        int i1 = LocalThreadTest.setAndGet(1);
        System.out.println(Thread.currentThread().getName() + ",localInt=" + i1);
        LocalThreadTest.remove();
        System.out.println(Thread.currentThread().getName() + ",localInt=" + i1);

        System.out.println("------2------");
        int i2 = LocalThreadTest.setAndGet(2);
        System.out.println(Thread.currentThread().getName() + ",localInt=" + i2);
        LocalThreadTest.remove();
        System.out.println(Thread.currentThread().getName() + ",localInt=" + i2);

        System.out.println("----threadLocal---所有线程初始化-----");
        // 所有线程初始化
        System.out.println(Thread.currentThread().getName() + ",threadLocal=" + threadLocal.get());

        System.out.println("----localInt--------");
        System.out.println(Thread.currentThread().getName() + ",localInt=" + localInt.get());
    }

}
