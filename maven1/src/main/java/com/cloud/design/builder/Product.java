package com.cloud.design.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品类，由多个部件组成。
 *
 * @author zl
 * @date 2019/10/22 14:12
 */
public class Product {
    private List<String> parts = new ArrayList<>();

    public void add(String part) {
        parts.add(part);
    }

    public void show() {
        System.out.println("产品创建 ---");
        for (String part : parts) {
            System.out.println(part);
        }
    }
}