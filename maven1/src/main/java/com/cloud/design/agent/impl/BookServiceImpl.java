package com.cloud.design.agent.impl;

import com.cloud.design.agent.Service;

/**
 * @author zl
 * @date 2022/10/23 16:58
 */
public class BookServiceImpl implements Service {
    @Override
    public void buy() {
        System.out.println("图书购买...............");
    }

    @Override
    public String show() {
        System.out.println("展示书本..................");
        return "这是一本书";
    }

    @Override
    public String toString() {
        return "BookServiceImpl{}";
    }
}
