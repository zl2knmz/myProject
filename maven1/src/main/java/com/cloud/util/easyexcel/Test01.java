package com.cloud.util.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author zl
 * @date 2022/5/6 17:17
 */
public class Test01 {

    /**
     * 最简单的写(方式一)
     */
    @Test
    public void testWriteExcel() {
        String filename = "D:\\work\\easyExcel\\user1.xlsx";

        // 向Excel中写入数据 也可以通过 head(Class<?>) 指定数据模板
        EasyExcel.write(filename, User.class)
                .sheet("用户信息")
                .doWrite(getUserData());
    }

    /**
     * 最简单的写(方式二)
     */
    @Test
    public void testWriteExcel2() {
        String filename = "D:\\work\\easyExcel\\user2.xlsx";
        // 创建ExcelWriter对象
        ExcelWriter excelWriter = EasyExcel.write(filename, User.class).build();
        // 创建Sheet对象
        WriteSheet writeSheet = EasyExcel.writerSheet("用户信息").build();
        // 向Excel中写入数据
        excelWriter.write(getUserData(), writeSheet);
        // 关闭流
        excelWriter.finish();
    }

    /**
     * 排除模型中的属性字段
     */
    @Test
    public void testWriteExcel3() {
        String filename = "D:\\work\\easyExcel\\user3.xlsx";
        // 设置排除的属性 也可以在数据模型的字段上加@ExcelIgnore注解排除
        Set<String> excludeField = new HashSet<>();
        excludeField.add("hireDate");
        excludeField.add("salary");
        // 写Excel
        EasyExcel.write(filename, User.class)
                .excludeColumnFiledNames(excludeField)
                .sheet("用户信息")
                .doWrite(getUserData());
    }

    /**
     * 向表格中导出指定属性
     */
    @Test
    public void testWriteExcel4() {
        String filename = "D:\\work\\easyExcel\\user4.xlsx";
        // 设置要导出的字段
        Set<String> includeFields = new HashSet<>();
        includeFields.add("userName");
        includeFields.add("hireDate");
        // 写Excel
        EasyExcel.write(filename, User.class)
                .includeColumnFiledNames(includeFields)
                .sheet("用户信息")
                .doWrite(getUserData());
    }

    /**
     * 将Java对象中指定的属性, 插入到Eexcel表格中的指定列(在Excel表格中进行列排序), 使用index属性指定列顺序.
     */
    @Test
    public void testWriteExcel5() {
        String filename = "D:\\work\\easyExcel\\user5.xlsx";
        // 向Excel中写入数据
        EasyExcel.write(filename, User.class)
                .sheet("用户信息")
                .doWrite(getUserData());
    }

    /**
     * 将Java对象中指定的属性, 插入到Eexcel表格中的指定列(在Excel表格中进行列排序), 使用index属性指定列顺序.
     */
    @Test
    public void testWriteExcel6() {
        String filename = "D:\\work\\easyExcel\\user6.xlsx";
        List<ComplexHeadUser> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ComplexHeadUser user = ComplexHeadUser.builder()
                    .userId(i)
                    .userName("大哥" + i)
                    .hireDate(new Date())
                    .build();
            users.add(user);
        }
        // 向Excel中写入数据
        EasyExcel.write(filename, ComplexHeadUser.class)
                .sheet("用户信息")
                .doWrite(users);
    }

    @Test
    public void testWriteExcel7() {
        List<ComplexHeadUser> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ComplexHeadUser user = ComplexHeadUser.builder()
                    .userId(i)
                    .userName("大哥" + i)
                    .hireDate(new Date())
                    .build();
            list.add(user);
        }

        // 导出Excel
        String sheetName = "Sheet1";
        // 定义Excel文件名
//        String fileName = sheetName + String.valueOf(System.currentTimeMillis()).substring(4, 13);
        String fileName = "exchange_export";
        EasyExcelUtil.export(null, fileName, sheetName, list, ComplexHeadUser.class);
    }

    @Test
    public void testWriteExcel8() {
//        MultipartFile file;
        // 导入Excel
        List<ComplexHeadUser> list = EasyExcelUtil.importExcel(null, ComplexHeadUser.class, null);
        assert list != null;
        for (ComplexHeadUser user : list) {
            System.out.println(user);
        }

    }


    /**
     * 根据user模板构建数据
     */
    private List<User> getUserData() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = User.builder()
                    .userId(i)
                    .userName("admin" + i)
                    .gender(i % 2 == 0 ? "男" : "女")
                    .salary(i * 1000.00)
                    .hireDate(new Date())
                    .build();
            users.add(user);
        }
        return users;
    }

}
