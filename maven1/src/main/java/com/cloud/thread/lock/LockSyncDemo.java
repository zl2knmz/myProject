package com.cloud.thread.lock;

/**
 * 从字节码角度分析synchronized实现
 *
 * 反编译命令
 * javap -c .\LockSyncDemo.class
 *
 * 详细信息 -v 输出附加信息（包括行号、本地变量表，反汇编等详细信息）
 * javap -v .\LockSyncDemo.class
 *
 * synchronized同步代码块 实现使用的是monitorenter和monitorexit指令
 * 一般情况就是1个enter对应2个exit
 *        6: monitorenter
 *        7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       10: ldc           #5                  // String hello synchronized code block
 *       12: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *       15: aload_1
 *       16: monitorexit
 *       17: goto          25
 *       20: astore_2
 *       21: aload_1
 *       22: monitorexit
 * 极端 m1方法里添加一个异常，一对一
 *
 * @author zl
 * @date 2023/4/6 23:02
 */
public class LockSyncDemo {
    /*Object object = new Object();
    public void m1() {
        synchronized (object) {
            System.out.println("hello synchronized code block");
            // 不推荐用，仅仅为了演示使用
            throw new RuntimeException("----exp");
        }
    }*/

    /**
     * 调用指令将会检查方法的 ACC_SYNCHRONIZED访问标志是否被设置。
     * 如果被设置了，执行线程会将先持有monitor锁，然后再执行方法，最后在方法完成（无论是正常完成还是非正常完成）时释放monitor
     */
    public synchronized void m2() {
        System.out.println("-------hello synchronized m2");
    }

    /**
     * ACC_STATIC, ACC_SYNCHRONIZED访问标志区分该方法是否静态同步方法
     */
    public static synchronized void m3() {
        System.out.println("---------hello static synchronized m3");
    }

    public static void main(String[] args) {


    }
}
