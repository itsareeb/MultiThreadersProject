package main.java.co.hsbc.models;

import main.java.co.hsbc.Enums.EmployeeEnums;

public class Admin extends main.java.co.hsbc.models.Employee {
    public Admin(int empId, EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email) {
        super(empId, role, empName, password, isActive, contact, email);
    }

}
