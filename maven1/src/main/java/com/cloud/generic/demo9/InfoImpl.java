package com.cloud.generic.demo9;

/**
 * @author zl
 * @date 2022/5/3 21:47
 */
public class InfoImpl implements Info<Integer> {
    @Override
    public Integer info(Integer value) {
        return value;
    }
}
