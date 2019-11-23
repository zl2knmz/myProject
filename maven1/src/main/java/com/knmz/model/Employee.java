package com.knmz.model;

import com.google.common.base.MoreObjects;

/**
 * @author zl
 * @date 2019/10/18 18:39
 */
public class Employee {
    private final String name;
    private final String dept;
    private final String empID;

    public Employee(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.empID = empID;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getEmpID() {
        return empID;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Name", this.getName())
                .add("Department", getDept())
                .add("EmployeeID", this.getEmpID()).toString();
    }
}
