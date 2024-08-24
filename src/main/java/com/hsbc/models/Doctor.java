package com.hsbc.models;

import com.hsbc.Enums.EmployeeEnums;

public class Doctor extends Employee {
    private String qualifications;
    private String specialization;

    EmployeeEnums.Department dept = EmployeeEnums.Department.general;

    public Doctor() {
    }

    public Doctor( EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email, String qualifications, String specialization) {
        super( role, empName, password, isActive, contact, email);
        this.qualifications = qualifications;
        this.specialization = specialization;
    }

    public Doctor( EmployeeEnums.Role role, String empName, String password, boolean isActive, String contact, String email, String qualifications, String specialization, EmployeeEnums.Department dept) {
        super(role, empName, password, isActive, contact, email);
        this.qualifications = qualifications;
        this.specialization = specialization;
        this.dept = dept;
    }

    public EmployeeEnums.Department getDept() {
        return dept;
    }

    public void setDept(EmployeeEnums.Department dept) {
        this.dept = dept;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return super.toString() + "Doctor{" +
                "qualifications='" + qualifications + '\'' +
                ", specialization='" + specialization + '\'' +
                ", dept=" + dept +
                '}';
    }
}
