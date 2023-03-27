package com.cloud.thread.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程、有返回值、异步任务
 * Callable Runnable Future FutureTask
 * @author zl
 * @date 2023/3/27 23:12
 */
public class Demo4 {

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        Thread thread1 = new Thread(futureTask, "thread1");
        thread1.start();

        try {
            String s = futureTask.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }




}

class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("---------come in call()-------");
        return "hello call ";
    }
}