package com.hsbc.models;

import com.hsbc.Enums.EmployeeEnums;

public class User extends Employee{
    private EmployeeEnums.Department dept = EmployeeEnums.Department.general;
    private EmployeeEnums.Shift shift;

    public User(EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email, EmployeeEnums.Department dept, EmployeeEnums.Shift shift) {
        super(role, empName, password, isActive, contact, email);
        this.dept = dept;
        this.shift = shift;
    }

    public User(EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email, EmployeeEnums.Shift shift) {
        super( role, empName, password, isActive, contact, email);
        this.shift = shift;
    }

    public User() {

    }

    public User(int i, String johnDoe, EmployeeEnums.Role role, String number, String mail, boolean b) {
    }


    public EmployeeEnums.Department getDept() {
        return dept;
    }

    public void setDept(EmployeeEnums.Department dept) {
        this.dept = dept;
    }

    public EmployeeEnums.Shift getShift() {
        return shift;
    }

    public void setShift(EmployeeEnums.Shift shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return super.toString() +  "User{" +
                "dept=" + dept +
                ", shift=" + shift +
                '}';
    }
}
