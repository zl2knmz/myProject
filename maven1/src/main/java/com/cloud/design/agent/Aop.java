package com.cloud.design.agent;

/**
 * 切面接口
 *
 * @author zl
 * @date 2022/10/23 16:21
 */
public interface Aop {
    default void before() {
    }

    default void after() {
    }

    default void exception() {
    }
}
