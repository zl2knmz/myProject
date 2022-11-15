package com.cloud.thread.threadlocal;

import org.junit.Test;

/**
 * @author zl
 * @date 2022/10/31 14:00
 */
public class MyTest {
    @Test
    public void testWithMap() {
        SharedMapWithUserContext firstUser = new SharedMapWithUserContext(1);
        SharedMapWithUserContext secondUser = new SharedMapWithUserContext(2);
        new Thread(firstUser).start();
        new Thread(secondUser).start();
        System.out.println(SharedMapWithUserContext.userContextPerUserId.size());
//        assertEquals(SharedMapWithUserContext.userContextPerUserId.size(), 1);
    }

    @Test
    public void testWithThreadLocal() {
        ThreadLocalWithUserContext firstUser
                = new ThreadLocalWithUserContext(1);
        ThreadLocalWithUserContext secondUser
                = new ThreadLocalWithUserContext(2);
        new Thread(firstUser).start();
        new Thread(secondUser).start();
    }
}
