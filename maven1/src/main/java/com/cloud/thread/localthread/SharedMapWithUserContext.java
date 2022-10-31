package com.cloud.thread.localthread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zl
 * @date 2022/10/31 13:52
 */
public class SharedMapWithUserContext implements Runnable {

    public static Map<Integer, UserRepository> userContextPerUserId = new ConcurrentHashMap<>();
    private Integer userId;
    private UserRepository userRepository = new UserRepository();

    public SharedMapWithUserContext(int i) {
        this.userId = i;
    }

    @Override
    public void run() {
        String userName = userRepository.getUserNameForUserId(userId);
        userRepository.setId(userId);
        userRepository.setName(userName);
        userContextPerUserId.put(userId, userRepository);
        System.out.println("thread context for given userId: "
                + userId + " is: " + userContextPerUserId.get(userId));
    }
}
