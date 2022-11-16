package com.cloud.thread.threadlocal.praent;

/**
 * InheritableThreadLocal 子线程可以拿到父线程的中的 ThreadLocal 值
 *
 * 如果设置了 inheritableThreadLocals 变量，那么 Thread 就会把父线程 ThreadLocal threadLocals
 * 中的所有数据都 copy 到子线程的 InheritableThreadLocal inheritableThreadLocals 中。
 * 而且，copy 调用的 createInheritedMap 方法其实是一个浅拷贝函数，key 和 value 都是原来的引用地址，
 * 这里所谓的 copy 其实就是把一个 Map 中的数据复制到另一个 Map 中：
 *
 * 1、在创建 InheritableThreadLocal 对象的时候赋值给线程的 t.inheritableThreadLocals 变量
 * 2、在创建新线程的时候会 check 父线程中 t.inheritableThreadLocals 变量是否为 null，
 *    如果不为 null 则 copy 一份数据到子线程的 t.inheritableThreadLocals 成员变量中去
 * 3、InheritableThreadLocal 重写了 getMap(Thread) 方法，所以 get 的时候，就会从 t.inheritableThreadLocals 中拿到 ThreadLocalMap 对象，
 *    从而实现了可以拿到父线程 ThreadLocal 中的值
 *
 * @author zl
 * @date 2022/11/14 23:09
 */
public class InheritableThreadLocalTest {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    private static final InheritableThreadLocal<String> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void main(String[] args) throws Exception {
        THREAD_LOCAL.set("飞天小牛肉");
        System.out.println(Thread.currentThread().getName() + ",父线程的值：" + THREAD_LOCAL.get());
        test();

        // 子线程拿到父线程的中的 ThreadLocal 值
        INHERITABLE_THREAD_LOCAL.set("糖醋排骨");
        System.out.println(Thread.currentThread().getName() + ",父线程的值：" + INHERITABLE_THREAD_LOCAL.get());
        test1();
    }

    private static void test() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ",子线程的值：" + THREAD_LOCAL.get());
            }
        }).start();
        Thread.sleep(2000);
        THREAD_LOCAL.remove();
    }

    private static void test1() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ",子线程的值：" + INHERITABLE_THREAD_LOCAL.get());
            }
        }).start();
        Thread.sleep(2000);
    }
}
