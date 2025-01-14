package com.cloud.model;

import lombok.Data;

/**
 * @author zl
 * @date 2024/11/21 13:42
 */
@Data
public class MyObject {
    private Long id;
    private String name;

    public MyObject(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
