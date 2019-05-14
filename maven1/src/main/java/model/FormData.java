package model;

import lombok.Data;

/**
 * @Author: zl
 * @Date: 2019/5/14 11:16
 * 解析活动表中报名表单数据
 */
@Data
public class FormData {

    /**
     * 公司：I_10001
     */
    private String company;

    /**
     * 职位：I_10003
     */
    private String job;

    /**
     * 学历：I_10009
     */
    private String degree;
}
