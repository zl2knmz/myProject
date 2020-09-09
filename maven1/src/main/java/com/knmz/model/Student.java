package com.knmz.model;

import lombok.Data;

/**
 * @author zl
 * @date 2020/6/16 19:52
 */
@Data
public class Student {
    private String name;
    private String dept;
    private String empID;

    public Student() {}

    public Student(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.empID = empID;
    }
}
