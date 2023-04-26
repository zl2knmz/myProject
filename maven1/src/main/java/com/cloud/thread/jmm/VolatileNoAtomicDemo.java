package com.cloud.thread.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @author zl
 * @date 2023/4/26 23:30
 */
public class VolatileNoAtomicDemo {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myNumber.number);
    }
}

class MyNumber {
    /**
     * volatile不保证原子性
     */
//    volatile int number;
//
//    public void addPlusPlus() {
//        number++;
//    }

    int number;

    public synchronized void addPlusPlus() {
        number++;
    }
}