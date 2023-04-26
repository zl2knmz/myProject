package com.cloud.thread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zl
 * @date 2023/4/26 16:24
 */
public class TicketSeller1 implements Runnable {
    private int tickets = 10;
    private Object lock = new Object();

    @Override
    public void run() {
        synchronized (lock) {
            if (tickets > 0) {
                try {
                    // 模拟卖票时间
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖出了第" + tickets + "张票");
                tickets--;
            } else {
                System.out.println(Thread.currentThread().getName() + "没有票了");

            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        TicketSeller1 ticketSeller = new TicketSeller1();
        for (int i = 0; i < 20; i++) {
            executor.execute(ticketSeller);
        }
        executor.shutdown();
    }
}
