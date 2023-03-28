package com.cloud.thread.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * futureTask.get() 对结果的获取不友好，只能通过 阻塞/轮询 的方式获取返回结果
 * 1、get容易导致阻塞，一般不建议放在程序后面，一旦调用就要等到结果才会离开，不管你是否计算完成，容易程序堵塞。
 * 2、假如我不愿意等待很长时间，我希望过时不候，可以自动离开。
 *
 * @author zl
 * @date 2023/3/28 23:07
 */
public class Demo6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------come in");
            // 复杂计算
            TimeUnit.SECONDS.sleep(5);
            return "task1 over";
        });
        Thread thread = new Thread(futureTask, "t1");
        thread.start();

        System.out.println(Thread.currentThread().getName() + "\t ------忙其他任务了");

        // 阻塞程序，不管是否计算完，非要等到返回值才离开（一般get()放在程序的最后 防止阻塞程序）
//        System.out.println(futureTask.get());

        // 假如我不愿意等待很长时间，我希望过时不候，可以自动离开。
//        System.out.println(futureTask.get(3, TimeUnit.SECONDS));

        // 轮询返回结果 （耗费CPU性能，也不见得能及时的得到计算结果）
        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                // 间隔500毫秒查看一次是否有返回结果了
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在处理中，请稍后。");
            }
        }
    }
}
