package com.hsbc.models;

import com.hsbc.Enums.EmployeeEnums;

public class Admin extends Employee {
    public Admin(int empId, EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email) {
        super(empId, role, empName, password, isActive, contact, email);
    }

}
