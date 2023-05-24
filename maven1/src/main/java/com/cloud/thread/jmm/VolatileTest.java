package com.cloud.thread.jmm;

/**
 * volatile写之前的操作，都禁止重排序到volatile之后
 * volatile读之后的操作，都禁止重排序到volatile之前
 * volatile写之后volatile读，禁止重排序
 *
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
