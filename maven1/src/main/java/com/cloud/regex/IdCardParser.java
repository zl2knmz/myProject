package com.cloud.regex;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zl
 * @date 2023/8/28 16:18
 */
public class IdCardParser {

    public static void main(String[] args) {

        String ID_18 = "141122198804120078";
        String ID_15 = "150102800730303";

        //是否有效
        boolean valid = IdcardUtil.isValidCard(ID_18);
        System.out.println(valid);


        String pattern = "yyyy-MM-dd";
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        String nowYear = LocalDateTime.now().format(df);
        DateTime date = DateUtil.parse(nowYear);
        // 年龄
        int age = IdcardUtil.getAgeByIdCard(ID_18, date);
        System.out.println(age);
        System.out.println(IdcardUtil.getAgeByIdCard(ID_18));

        // 生日
        String birthByIdCard = IdcardUtil.getBirthByIdCard(ID_18);
        System.out.println(birthByIdCard);
        System.out.println(IdcardUtil.getBirthDate(ID_18));

        // 性别
        int gender = IdcardUtil.getGenderByIdCard(ID_18);
        System.out.println(gender);
        System.out.println(gender > 0 ? "男" : "女");

        // 省份
        String province = IdcardUtil.getProvinceByIdCard(ID_18);
        System.out.println(province);
        System.out.println(IdcardUtil.getCityCodeByIdCard(ID_18));


        String falg = "456";
        switch (falg){
            case "123":
            case "789":
                System.out.println(falg);
                break;
            case "456":
                System.out.println(falg);
                break;
            default:
                System.out.println(falg);
        }
    }
}
