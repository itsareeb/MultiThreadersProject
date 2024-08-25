package com.hsbc.models;

import com.hsbc.Enums.EmployeeEnums;

import java.util.Objects;

public class Employee {
    private int empId;

    private EmployeeEnums.Role role;
    private String empName;
    private String password;
    private boolean isActive;
    private String contact;
    private String email;

    public Employee() {
    }

    public Employee(EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email) {
        this.role = role;
        this.empName = empName;
        this.password = password;
        this.isActive = isActive;
        this.contact = contact;
        this.email = email;
    }

    public Employee(int Empid, EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email) {
        this.empId=Empid;
        this.role = role;
        this.empName = empName;
        this.password = password;
        this.isActive = isActive;
        this.contact = contact;
        this.email = email;
    }


    public static void main(String[] args) {
        Employee employee = new Employee(
                EmployeeEnums.Role.doctor,
                "Areeb",
                "Password",
                true,
                "6789098767",
                "mdareeb176@dfg.fref"
        );
    }


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public EmployeeEnums.Role getRole() {
        return role;
    }

    public void setRole(EmployeeEnums.Role role) {
        this.role = role;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", role=" + role +
                ", empName='" + empName + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empId == employee.empId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(empId);
    }
}
