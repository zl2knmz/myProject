package com.knmz.util;

import java.io.*;

/**
 * @Author: zl
 * @Date: 2019/8/28 13:32
 */
public class FileStreamUtil {
    /**
     * 读入TXT文件
     *
     * @param pathName 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
     */
    public static StringBuilder readFile(String pathName) {
        StringBuilder stringBuilder = new StringBuilder();
        // 不关闭文件会导致资源的泄露，读写文件都同理;Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；
        try (FileReader reader = new FileReader(pathName);
             // 建立一个对象，它把文件内容转成计算机能读懂的语言
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line = null;
            // 网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
            e.printStackTrace();
        }
        return stringBuilder;
    }

    /**
     * 写入TXT文件
     *
     * @param pathName 文件名
     * @param context  文件内容
     */
    public static void writeFile(String pathName, String context) {
        try {
            // 相对路径，如果没有则要建立一个新的output.txt文件
            File writeName = new File(pathName);
            // 创建新文件,有同名的文件的话直接覆盖
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(context);
                // 把缓存区内容压入文件
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
