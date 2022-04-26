package com.cloud.test;

import com.cloud.util.FileStreamUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zl
 * @date 2019/8/28 11:58
 */
public class FileStreamTest {
    public static void main(String[] args) {
        // 读txt文件
        String pathName = "D:\\Program Files\\image\\phone.txt";
//        String pathName = "E:\\Program Files\\image\\email.txt";
        StringBuilder stringBuilder = FileStreamUtil.readFile(pathName);
        List<String> stringList = Arrays.asList(stringBuilder.toString().split(","));
        System.out.println("读到个数：" + stringList.size());
        // 去重
        List<String> stringListDistinct = stringList.stream().distinct().collect(Collectors.toList());
        System.out.println("去重后读到个数：" + stringListDistinct.size());

        // 写txt文件
        String pathNameWrite = "D:\\Program Files\\image\\output.txt";
//        String context = "写文件啦\r\n";
//        FileStreamUtil.writeFile(pathNameWrite, context);

        /*StringBuilder stringBuilderWrite = new StringBuilder();
        if (stringList.size() > 0) {
            int i = 0;
            for (String data : stringList) {
                if (i >= 900 && null != data) {
                    stringBuilderWrite.append(data);
                    stringBuilderWrite.append(",");
                }
                i++;
            }
            //去掉末尾的分隔符
            stringBuilderWrite.deleteCharAt(stringBuilderWrite.length() - 1);
            FileStreamUtil.writeFile(pathNameWrite, stringBuilderWrite.toString());

            List<String> stringWriteList = Arrays.asList(stringBuilderWrite.toString().split(","));
            System.out.println("写到个数：" + stringWriteList.size());
        }*/
    }
}
