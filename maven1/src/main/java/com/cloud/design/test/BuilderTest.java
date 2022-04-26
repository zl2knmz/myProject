package com.cloud.design.test;

import com.cloud.design.builder.*;

/**
 * 建造者测试类
 * @author zl
 * @date 2019/10/22 14:36
 */
public class BuilderTest {
    public static void main(String[] args) {
        // 指挥者
        Director director = new Director();

        // 产品一
        BaseBuilder builder1 = new ConcreteBuilder1();
        // 添加产品一的部件
        director.construct(builder1);
        // 产品生成
        Product product1 = builder1.getResult();
        // 展示产品部件
        product1.show();

        BaseBuilder builder2 = new ConcreteBuilder2();
        director.construct(builder2);
        Product product2 = builder2.getResult();
        product2.show();
    }
}