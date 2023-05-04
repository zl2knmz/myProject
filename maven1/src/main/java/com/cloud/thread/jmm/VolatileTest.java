package com.cloud.thread.jmm;

/**
 * @author zl
 * @date 2023/5/4 23:15
 */
public class VolatileTest {
    int i = 0;
    volatile boolean flag = false;

    public void write() {
        i = 2;
        flag = true;
    }

    public void read() {
        if (flag) {
            System.out.println("------i = " + i);
        }
    }
}
