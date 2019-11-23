package com.knmz.design.builder;

/**
 * 具体建造者类
 *
 * @author zl
 * @date 2019/10/22 14:28
 */
public class ConcreteBuilder1 extends BaseBuilder {
    private Product product = new Product();

    @Override
    public void buildPart1() {
        product.add("部件1");
    }

    @Override
    public void buildPart2() {
        product.add("部件2");
    }

    @Override
    public Product getResult() {
        return product;
    }
}