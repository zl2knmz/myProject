package com.cloud.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 磨刀不误砍柴工，B站毕业再打工。
 *
 * 锁的种类：
 *
 * 乐观锁：认为自己在使用数据时不会有别的线程修改数据或资源，所以不会添加锁。
 *        在Java中是通过使用无锁编程来实现，只是在更新数据的时候去判断，之前有没有别的线程更新了这个数据。
 *        如果这个数据没有被更新，当前线程将自己修改的数据成功写入。
 *        如果这个数据已经被其他线程更新，则根据不同的实现方式执行不同的操作，比如放弃修改、重试抢锁等等
 *
 *        判断规则
 *        1、版本号机制Version
 *        2、最常采用的是CAS算法，Java原子类中的递增操作就通过CAS（比较并交换）自旋实现的。
 *
 *        适合读操作多的场景，不加锁的特点能够使其读操作的性能大幅提升。
 *        乐观锁则直接去操作同步资源，是一种无锁算法，得之我幸不得我命，再努力就是。
 *        一句话：佛系锁
 *        乐观锁一般有两种实现方式：1、采用Version版本号机制
 *                               2、CAS（Compare-and-Sswap，即比较并替换）算法实现
 *
 * 悲观锁：认为自己在使用数据的时候一定有别的线程来修改数据，因此在获取数据的时候会先加锁，确保数据不会被别的线程修改。
 *         synchronize关键字和Lock的实现类都是悲观锁
 *         适合写操作多的场景，先加锁可以保证写操作时数据正确。
 *         显示的锁定之后再操作同步资源
 *         一句话：狼性锁
 *
 * 公平锁、非公平锁
 * 可重入锁
 * 死锁及排查
 * 自旋锁 SpinLock
 * 无锁 -> 独占锁 -> 读写锁 -> 邮戳锁
 * 无锁 -> 偏向锁 -> 轻量锁 -> 重量锁
 * @author zl
 * @date 2023/4/5 18:05
 */
public class Demo {

    /**悲观锁的调用方式*/
    public synchronized void m1() {
        // 加锁后的业务逻辑......
    }

    /**保证多个线程使用的是同一个lock对象的前提下*/
    ReentrantLock lock = new ReentrantLock();
    public synchronized void m2() {
        lock.lock();
        try {
            // 操作同步资源
        } finally {
            lock.unlock();
        }
    }

    /**保证多个线程使用的是同一个AtomicInteger*/
    private AtomicInteger atomicInteger = new AtomicInteger();
    private void addAtomicInteger() {
        atomicInteger.incrementAndGet();
    }


}
