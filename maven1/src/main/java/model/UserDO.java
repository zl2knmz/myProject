package model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

/**
 * @Author: zl
 * @Date: 2019/3/17 21:55
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
}
