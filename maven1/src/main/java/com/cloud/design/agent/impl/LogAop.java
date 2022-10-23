package com.cloud.design.agent.impl;

import com.cloud.design.agent.Aop;

/**
 * @author zl
 * @date 2022/10/23 16:57
 */
public class LogAop implements Aop {

    @Override
    public void before() {
        System.out.println("日志输出..............");
    }

}
