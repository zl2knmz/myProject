package com.knmz.util;

import java.io.*;

/**
 * @author zl
 * @date 2019/6/26 11:48
 */
public class FileOutStreamUtil {

    /**
     * 字符流 (输出流中含有中文时使用字符流)
     *
     */
    public static void charOutStream(String filePath, String fileName, String content) {
        // 1：利用File类找到要操作的对象
        File file = new File(filePath + fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        //2：准备输出流
        try {
            Writer out = new FileWriter(file);
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 字节流
     */
    public static void byteOutStream(String filePath, String fileName, String content) {

        //1:使用File类创建一个要操作的文件路径
        File file = new File(filePath + fileName);
        //如果文件的目录不存在
        if(!file.getParentFile().exists()){
            //创建目录
            file.getParentFile().mkdirs();
        }

        try {
            //2: 实例化OutputString 对象
            OutputStream output = new FileOutputStream(file);

            //3: 准备好实现内容的输出,将字符串变为字节数组
            byte[] data = content.getBytes();
            output.write(data);

            //4: 资源操作的最后必须关闭
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
