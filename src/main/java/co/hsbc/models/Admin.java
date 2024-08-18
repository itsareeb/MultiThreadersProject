package main.java.co.hsbc.models;

import main.java.co.hsbc.Enums.Employee;

public class Admin extends main.java.co.hsbc.models.Employee {
    public Admin(int empId, Employee.Role role, String empName, String password, boolean isActive, String contact, String email) {
        super(empId, role, empName, password, isActive, contact, email);
    }

    public Admin(int empId, Employee.Role role, String empName, String password, boolean isActive, String contact) {
        super(empId, role, empName, password, isActive, contact);
    }

}
