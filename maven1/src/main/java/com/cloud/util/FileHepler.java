package com.cloud.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 文件操作工具类
 *
 * @author zl
 * @date 2021/5/14 15:24
 */
public class FileHepler {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileHepler.class);

    public static DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String getFileData(String filePath) throws IOException {
        String result = "";
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
            if (lines.size() > 0) {
                result = lines.get(0);
            }
        } catch (Exception ex) {
            LOGGER.error("getFileData error.", ex);
        }
        return result;
    }

    public static void setDataToFile(String filePath, String data) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        FileUtils.writeStringToFile(new File(filePath), data, "UTF-8", true);
    }

    public static void main(String[] args) throws IOException {
        String jsonBehavior = "";
        String fileDate = FileHepler.DateFormat.format(LocalDateTime.now());
//        String filePath = "C:\\Users\\admin\\Desktop\\push-temp\\behavior\\" + fileDate + ".txt";
        String filePath = "/data/datax/datax-api/behavior/" + fileDate + ".txt";
        FileHepler.setDataToFile(filePath, jsonBehavior + "\n");
    }
}
