package com.cloud.thread.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zl
 * @date 2023/4/1 0:05
 */
public class Demo8 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "hello 1234";
        });
        // get方法需要处理异常 throws ExecutionException, InterruptedException
//        System.out.println(completableFuture.get());

        // join方法不需要处理异常
        System.out.println(completableFuture.join());
    }

}

/**
 * // @Accessors(chain = true)
 * 链式赋值 new Student().setId(1).setStudentName("mac").setMajor("english");
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
class Student {
    private Integer id;
    private String studentName;
    private String major;
}