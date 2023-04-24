package com.cloud.regex;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author zl
 * @date 2023/4/21 10:24
 */
public class Demo {
    @Test
    public void m1() {
        String content = "I am noob from .runoob..com.";
        String pattern = ".*runoob.*";
        boolean result = Pattern.matches(pattern, content);
        System.out.println(result);
    }
}
