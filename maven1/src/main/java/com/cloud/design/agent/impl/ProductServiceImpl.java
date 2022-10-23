package com.cloud.design.agent.impl;

import com.cloud.design.agent.Service;

/**
 * @author zl
 * @date 2022/10/23 16:58
 */
public class ProductServiceImpl implements Service {

    @Override
    public void buy() {
        System.out.println("产品购买................");
    }

    @Override
    public String toString() {
        return "ProductServiceImpl{}";
    }
}
