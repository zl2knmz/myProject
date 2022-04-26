package com.cloud.design.builder;

/**
 * 指挥者类
 * @author zl
 * @date 2019/10/22 14:32
 */
public class Director {
    public void construct(BaseBuilder builder){
        builder.buildPart1();
        builder.buildPart2();
    }
}