package com.cloud.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

/**
 * @author zl
 * @date 2019/3/17 21:55
 */
@Data
public class UserDO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门
     */
    private String department;

    /**
     * 工号
     */
    @NonNull
    private String empId;

    /**
     * 权限
     */
    private Integer privilege;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private String op_time;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    public UserDO() {}

    public UserDO(Long id, String name, String department, String empId, Integer privilege, String phone, String pwd, String operator, String op_time, Integer status, Date createDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.empId = empId;
        this.privilege = privilege;
        this.phone = phone;
        this.pwd = pwd;
        this.operator = operator;
        this.op_time = op_time;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
