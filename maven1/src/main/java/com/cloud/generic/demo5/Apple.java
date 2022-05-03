package com.cloud.generic.demo5;

/**
 * 实现泛型接口的类，不是泛型类，需要明确实现泛型接口的数据类型。
 * @author zl
 * @date 2022/5/3 15:23
 */
public class Apple implements Generator<String> {
    @Override
    public String getKey() {
        return "hello generator";
    }
}
