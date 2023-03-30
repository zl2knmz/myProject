package com.cloud.test;

import com.entity.User;

/**
 * @author zl
 * @date 2023/3/30 17:09
 */
public class EntityTest {
    public static void main(String[] args) {
        // GenerateAllSetter  idea插件 自动设置对象参数
        User user = new User();
        user.setId(0L);
        user.setName("");
        user.setPassword("");

        System.out.println(user);
    }
}
