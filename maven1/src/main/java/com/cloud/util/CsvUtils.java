package com.cloud.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zl
 * @date 2024/6/27 17:50
 */
public class CsvUtils {

    public static void main(String[] args) {
        String test = "hdx";
        switch (test){
            case "hdx":
                System.out.println("111111111");
            case "hdb":
                System.out.println("222222222222");
            case "bg":
                System.out.println("333333333333");
                break;
            default:
                System.out.println("00000000000");
                break;
        }

    }


    public static void main1(String[] args) {
        // CSV文件路径
        String csvFilePath = "D:\\data\\data.csv";

        // 要写入的数据
        List<List<String>> dataToWrite = new ArrayList<>();
        dataToWrite.add(Arrays.asList("Name", "Age", "City"));
        dataToWrite.add(Arrays.asList("John Doe", "30", "New York"));
        dataToWrite.add(Arrays.asList("Jane Doe", "25", "London"));

        // 写入CSV文件
        writeCsv(csvFilePath, dataToWrite);

        // 读取CSV文件
        List<List<String>> dataRead = readCsv(csvFilePath);

        // 打印读取的数据
        System.out.println("读取的CSV数据：");
        for (List<String> row : dataRead) {
            System.out.println(row);
        }
        System.out.println("读取的CSV第一列数据：");
        for (List<String> row : dataRead) {
            System.out.println(row.get(0));
        }
    }

    /**
     * 写入数据到CSV文件
     *
     * @param filePath CSV文件路径
     * @param data     要写入的数据
     */
    public static void writeCsv(String filePath, List<List<String>> data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (List<String> row : data) {
                StringBuilder line = new StringBuilder();
                for (String value : row) {
                    line.append("\"").append(value.replaceAll("\"", "\"\"")).append("\"").append(",");
                }
                line.deleteCharAt(line.length() - 1); // 删除最后一个逗号
                writer.write(line.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从CSV文件读取数据
     *
     * @param filePath CSV文件路径
     * @return 读取的数据列表
     */
    public static List<List<String>> readCsv(String filePath) {
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> row = parseCsvLine(line);
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 解析CSV文件的一行数据
     *
     * @param line CSV文件的一行数据
     * @return 解析后的数据列表
     */
    private static List<String> parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();
        boolean inQuotes = false;
        for (char c : line.toCharArray()) {
            if (c == ',' && !inQuotes) {
                values.add(currentValue.toString().trim());
                currentValue = new StringBuilder();
            } else if (c == '"' && !inQuotes) {
                inQuotes = true;
            } else if (c == '"' && inQuotes) {
                inQuotes = false;
            } else {
                currentValue.append(c);
            }
        }
        values.add(currentValue.toString().trim());
        return values;
    }
}