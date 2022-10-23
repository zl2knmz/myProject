package com.cloud.design.agent.impl;

import com.cloud.design.agent.Aop;

/**
 * @author zl
 * @date 2022/10/23 16:57
 */
public class TransAop implements Aop {

    @Override
    public void before() {
        System.out.println("事物开启..............");
    }

    @Override
    public void after() {
        System.out.println("事物提交..............");
    }

    @Override
    public void exception() {
        System.out.println("事物回滚..............");
    }
}
