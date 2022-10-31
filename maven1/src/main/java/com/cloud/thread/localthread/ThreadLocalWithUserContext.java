package com.cloud.thread.localthread;


/**
 * @author zl
 * @date 2022/10/31 13:57
 */
public class ThreadLocalWithUserContext implements Runnable {

    private static ThreadLocal<UserRepository> userContext
            = new ThreadLocal<>();
    private Integer userId;
    private UserRepository userRepository = new UserRepository();

    public ThreadLocalWithUserContext(int i) {
        this.userId = i;
    }

    @Override
    public void run() {
        String userName = userRepository.getUserNameForUserId(userId);
        userRepository.setId(userId);
        userRepository.setName(userName);
        userContext.set(userRepository);
        System.out.println("thread context for given userId: "
                + userId + " is: " + userContext.get());
    }

}
