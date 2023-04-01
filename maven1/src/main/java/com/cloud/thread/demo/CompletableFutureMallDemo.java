package com.cloud.thread.demo;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 案例说明：电商比价需求，模拟如下情况
 * 1、需求
 * 1.1 同一款产品，同时搜索出同款产品在各大电商平台的售价；
 * 1.2 同一款产品，同时搜搜出本产品在同一个电商平台下，各个入驻卖家售价是多少；
 * <p>
 * 2、输出：出结果希望是同款产品在不同地方的价格清单列表，返回一个List<String>
 * 《mysql》 in jd price is 88.05
 * 《mysql》 in dangdaang price is 86.11
 * 《mysql》 in taobao price is 90.43
 * <p>
 * 3、技术要求
 * 3.1、函数式编程
 * 3.2、链式编程
 * 3.3、Stream流式计算
 * 3.4 CompletableFuture 异步多线程
 *
 * @author zl
 * @date 2023/4/1 22:48
 */
public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao"),
            new NetMall("pdd"),
            new NetMall("tmaio")
    );

    /**
     * 单线程一个一个的处理任务
     */
    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    /**
     * 多线程处理任务
     * List<String> -------> List<CompletableFuture<String>> -------> List<String>
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(() ->
                        String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName)))
                ).collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        // 单线程 (5128毫秒)
        List<String> list1 = CompletableFutureMallDemo.getPrice(list, "mysql");
        for (String element : list1) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("---------costTime: " + (endTime - startTime) + "毫秒");

        System.out.println("-------------------------");

        long startTime2 = System.currentTimeMillis();
        // 多线程 (1129毫秒)
        List<String> list2 = CompletableFutureMallDemo.getPriceByCompletableFuture(list, "mysql");
        for (String element : list2) {
            System.out.println(element);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("---------costTime: " + (endTime2 - startTime2) + "毫秒");

//        System.out.println(ThreadLocalRandom.current().nextDouble() * 2 + "mysql".charAt(0));
    }
}

class NetMall {
    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
