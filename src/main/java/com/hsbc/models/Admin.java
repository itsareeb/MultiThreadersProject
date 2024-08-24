package com.hsbc.models;

import com.hsbc.Enums.EmployeeEnums;

public class Admin extends Employee {
    public Admin( EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email) {
        super(role, empName, password, isActive, contact, email);
    }

}
