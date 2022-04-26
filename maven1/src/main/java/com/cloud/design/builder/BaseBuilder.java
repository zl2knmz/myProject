package com.cloud.design.builder;

/**
 * 建造者模式（Builder）：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 * 抽象建造者类，确定产品由两个部件组成，并定义一个获取产品建造后结果的方法。
 *
 * @author zl
 * @date 2019/10/22 14:17
 */
public abstract class BaseBuilder {
    /**
     * 部件一
     */
    public abstract void buildPart1();

    /**
     * 部件二
     */
    public abstract void buildPart2();

    /**
     * 获取产品建造结果
     *
     * @return Product 建造结果
     */
    public abstract Product getResult();
}