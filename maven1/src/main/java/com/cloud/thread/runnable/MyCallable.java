package com.cloud.thread.runnable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口创建线程
 *
 * @author zl
 * @date 2023/2/28 16:03
 */
public class MyCallable implements Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        String s = futureTask.get();
        System.out.println(s);
    }

    @Override
    public Object call() {
        return "hi call";
    }
}
