package com.cloud.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

/**
 * @author zl
 * @date 2019/7/11 17:03
 * 读写hdfs文件
 */
public class OperateHdfs {

    private static final String USER_NAME = "hue";

    /**
     * 读取hdfs上的文件内容
     */
    public static void readFromHdfs(String file) throws IOException {
        System.setProperty("HADOOP_USER_NAME", USER_NAME);
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(file), conf);
        Path path = new Path(file);
        FSDataInputStream in = fs.open(path);

        IOUtils.copyBytes(in, System.out, 4096, true);
        //使用FSDataInoutStream的read方法会将文件内容读取到字节流中并返回
         /*FileStatus stat = fs.getFileStatus(path);
         // create the buffer
         byte[] buffer = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
         is.readFully(0, buffer);
         is.close();
         fs.close();
         return buffer;*/
    }

    /**
     * 在指定位置新建一个文件，并写入字符
     */
    public static void writeToHdfs(String file, String words) throws IOException {
        System.setProperty("HADOOP_USER_NAME", USER_NAME);

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(file), conf);
        Path path = new Path(file);
        //创建文件
        FSDataOutputStream out = fs.create(path);

        //两个方法都用于文件写入，好像一般多使用后者
        //out.writeBytes(words);
        out.write(words.getBytes("UTF-8"));

        out.close();
        //如果是要从输入流中写入，或是从一个文件写到另一个文件（此时用输入流打开已有内容的文件）
        //可以使用如下IOUtils.copyBytes方法。
        //FSDataInputStream in = fs.open(new Path(args[0]));
        //4096为一次复制块大小，true表示复制完成后关闭流
        //IOUtils.copyBytes(in, out, 4096, true)
    }

    /**
     * 删除hdfs上的文件
     */
    public static void deleteHdfsFile(String file) throws IOException {
        System.setProperty("HADOOP_USER_NAME", USER_NAME);
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(file), conf);
        Path path = new Path(file);
        //查看fs的delete API可以看到三个方法。deleteonExit实在退出JVM时删除，下面的方法是在指定为目录是递归删除
        fs.delete(path, true);
        fs.close();
    }

    /**
     * 上传本地文件到hdfs
     */
    public static void uploadLocalFileHdfs(String src, String dst) throws IOException {
        System.setProperty("HADOOP_USER_NAME", USER_NAME);
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        Path pathDst = new Path(dst);
        Path pathSrc = new Path(src);

        fs.copyFromLocalFile(pathSrc, pathDst);
        fs.close();
    }

    /**
     * 显示目录下所有文件
     */
    public static void listDirAll(String DirFile) throws IOException {
        System.setProperty("HADOOP_USER_NAME", USER_NAME);

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(DirFile), conf);
        Path path = new Path(DirFile);

        FileStatus[] status = fs.listStatus(path);
        //方法1
        for (FileStatus f : status) {
            System.out.println(f.getPath().toString());
        }
        //方法2
        /*Path[] listedPaths = FileUtil.stat2Paths(status);
        for (Path p : listedPaths) {
            System.out.println(p.toString());
        }*/
    }

    public static void main(String[] args) throws IOException {

        String localFile = "C:\\Users\\Administrator\\Desktop\\push-temp\\test.txt";

        String path = "hdfs://master:8022/user/hue/push/";

        String file = "hdfs://192.168.1.220:8020/user/hue/push/test.txt";
//        String file = "hdfs://10.10.165.154:8020/user/hue/push/test.txt";

        String words = "测试向HDFS里面写文件！";

//        writeToHdfs(file, words);

//        uploadLocalFileHdfs(localFile, file);

        readFromHdfs(file);

//        listDirAll(path);

//        deleteHdfsFile(file);
    }
}