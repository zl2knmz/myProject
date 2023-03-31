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

//        System.out.println(completableFuture.get());
        System.out.println(completableFuture.join());


    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true) // 链式赋值 new Student().setId(1).setStudentName("mac").setMajor("english");
class Student {
    private Integer id;
    private String studentName;
    private String major;
}