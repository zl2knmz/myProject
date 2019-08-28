package com.knmz.test;

import com.knmz.util.FileStreamUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: zl
 * @Date: 2019/8/28 11:58
 */
public class FileStreamTest {
    public static void main(String[] args) {
        // 读txt文件
        StringBuilder stringBuilder = FileStreamUtil.readFile();
        List<String> stringList = Arrays.asList(stringBuilder.toString().split(","));
        System.out.println(stringList.size());

        // 写txt文件
//        FileStreamUtil.writeFile();
    }
}
