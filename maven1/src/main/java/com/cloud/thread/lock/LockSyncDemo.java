package com.cloud.thread.lock;

/**
 * 从字节码角度分析synchronized实现
 *
 * 1、反编译命令
 * javap -c .\LockSyncDemo.class
 *
 * 详细信息 -v 输出附加信息（包括行号、本地变量表，反汇编等详细信息）
 * javap -v .\LockSyncDemo.class
 *
 * synchronized同步代码块 实现使用的是monitorenter和monitorexit指令
 *   一般情况就是1个enter对应2个exit
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
 *   极端情况：m1方法里添加一个异常，一对一
 *
 *
 * 2、synchronized 锁的是什么（管程（Monitors）
 * 管程（Monitors 也称为监视器）是一种程序结构，结构内的多个子程序（对象或模板）形成的多个工作线程互斥访问共享资源。
 * 管程实现了在一个时间点，最多只有一个线程在执行管程的某个子程序。
 *
 * 执行线程就要求先成功持有管程，然后才能执行方法，最后当方法完成（无论是否正常完成还是非正常完成）时释放管程。
 * 在方法执行期间，执行线程持有了管程，其他任何线程都无法再获取到同一个管程。
 *
 * 在HotSpot虚拟机中，monitor采用ObjectMonitor实现
 *
 * D:\Program Files\download\openjdk-8u42-src-b03-14_jul_2022\openjdk\hotspot\agent\src\share\classes\sun\jvm\hotspot\runtime\ObjectMonitor.java
 * D:\Program Files\download\openjdk-8u42-src-b03-14_jul_2022\openjdk\hotspot\src\share\vm\runtime\objectMonitor.cpp
 * D:\Program Files\download\openjdk-8u42-src-b03-14_jul_2022\openjdk\hotspot\src\share\vm\runtime\objectMonitor.hpp
 *
 * ObjectMonitor.java -> ObjectMonitor.cpp -> objectMonitor.hpp
 * objectMonitor.hpp
 * 每个对象天生都带着一个对象监视器
 * 每一个被锁住的对象都会和 Monitor关联起来
 *
 * synchronized 必须作用于某个对象中，所以Java在对象的头文件（objectMonitor.hpp）存储了锁的相关信息。
 * 锁升级功能主要依赖于MarkWord中的锁标志位和释放偏向锁标志位。
 * objectMonitor.hpp 部分代码如下：
 *   ObjectMonitor() {
 *     _header       = NULL;
 *     _count        = 0; // 用来记录该线程获取锁的次数
 *     _waiters      = 0,
 *     _recursions   = 0; // 锁的重入次数
 *     _object       = NULL;
 *     _owner        = NULL; // 指向持有 ObjectMonitor对象的线程
 *     _WaitSet      = NULL; // 存放处于 wait状态的线程队列
 *     _WaitSetLock  = 0 ;
 *     _Responsible  = NULL ;
 *     _succ         = NULL ;
 *     _cxq          = NULL ;
 *     FreeNext      = NULL ;
 *     _EntryList    = NULL ; // 存放处于等待锁 block状态的线程队列
 *     _SpinFreq     = 0 ;
 *     _SpinClock    = 0 ;
 *     OwnerIsThread = 0 ;
 *     _previous_owner_tid = 0;
 *   }
 *
 *
 * @author zl
 * @date 2023/4/6 23:02
 */
public class LockSyncDemo {
    Object object = new Object();
    Book book = new Book();

    public void m1() {
//        synchronized (object) {
        synchronized (book) {
            System.out.println("hello synchronized code block");
            // 不推荐用，仅仅为了演示使用
            throw new RuntimeException("----exp");
        }
    }

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

class Book extends Object {
    // java = (C++)--
}
