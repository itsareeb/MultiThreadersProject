package main.java.co.hsbc.models;

import main.java.co.hsbc.Enums.EmployeeEnums;

public class User extends Employee{
    private EmployeeEnums.Department dept = EmployeeEnums.Department.general;
    private EmployeeEnums.Shift shift;

    public User(int empId, EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email, EmployeeEnums.Department dept, EmployeeEnums.Shift shift) {
        super(empId, role, empName, password, isActive, contact, email);
        this.dept = dept;
        this.shift = shift;
    }

    public User(int empId, EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email, EmployeeEnums.Shift shift) {
        super(empId, role, empName, password, isActive, contact, email);
        this.shift = shift;
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
