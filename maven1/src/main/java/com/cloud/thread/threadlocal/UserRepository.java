package com.cloud.thread.threadlocal;

import lombok.Data;

/**
 * @author zl
 * @date 2022/10/31 14:09
 */
@Data
public class UserRepository {
    private int id;

    private String name;

    public UserRepository() {
    }

    public UserRepository(int id) {
        this.id = id;
        this.name = String.valueOf(id);
    }

    public UserRepository(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getUserNameForUserId(int id) {
        return String.valueOf(id);
    }

}
