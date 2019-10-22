package com.knmz.design.test;

import com.knmz.design.builder.*;

/**
 * 建造者测试类
 * @author zl
 * @date 2019/10/22 14:36
 */
public class BuilderTest {
    public static void main(String[] args) {
        Director director = new Director();

        BaseBuilder builder1 = new ConcreteBuilder1();
        director.construct(builder1);
        Product product1 = builder1.getResult();
        product1.show();

        BaseBuilder builder2 = new ConcreteBuilder2();
        director.construct(builder2);
        Product product2 = builder2.getResult();
        product2.show();
    }
}