package com.cloud.design.agent;

/**
 * 业务接口
 *
 * @author zl
 * @date 2022/10/23 16:20
 */
public interface Service {
    void buy();

    /**
     * 接口的空实现
     * @return null
     */
    default String show () {
        return null;
    }
}
