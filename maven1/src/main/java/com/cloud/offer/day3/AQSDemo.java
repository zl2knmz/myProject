package com.cloud.offer.day3;

import java.util.HashMap;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java.util.concurrent -> JUC
 *
 * AbstractQueuedSynchronizer -> AQS
 *
 * Node
 *
 * Sync
 *
 * ReentrantLock 可重入锁
 * CountDownLatch 计数器
 * ReentrantReadWriteLock 读写锁
 * Semaphore 信号灯
 * 
 * @author zl
 * @date 2022/5/13 23:03
 */
public class AQSDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

//        LockSupport.park();
//        LockSupport.unpark();

        new HashMap<>(16).put(1,1);
    }
}
