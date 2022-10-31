package com.cloud.thread.localthread;


/**
 * @author zl
 * @date 2022/10/31 11:52
 */
public class LocalThreadTest {
    private static ThreadLocal<Integer> localInt = new ThreadLocal<>();

    // ThreadLocal提供的静态方法withInitial来初始化一个ThreadLocal。
    static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

//    public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
//        return new SuppliedThreadLocal<>(supplier);
//    }

    public static int setAndGet(int num){
        localInt.set(num);
        return localInt.get();
    }

    public static void remove(){
        localInt.remove();
    }

    public static void main(String[] args) {
        int i = LocalThreadTest.setAndGet(0);
        System.out.println(i);
        LocalThreadTest.remove();
        System.out.println(i);

        System.out.println("------------");
        int i1 = LocalThreadTest.setAndGet(1);
        System.out.println(i1);
        LocalThreadTest.remove();
        System.out.println(i1);

        System.out.println("------------");
        int i2 = LocalThreadTest.setAndGet(2);
        System.out.println(i2);
        LocalThreadTest.remove();
        System.out.println(i2);

        System.out.println("-------所有线程初始化-----");
        // 所有线程初始化
        System.out.println(threadLocal.get());
    }

}