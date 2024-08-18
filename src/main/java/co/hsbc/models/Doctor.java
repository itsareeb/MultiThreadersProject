package main.java.co.hsbc.models;

import main.java.co.hsbc.Enums.Employee;

public class Doctor extends main.java.co.hsbc.models.Employee {
    String qualifications;
    String specialization;

    Employee.Department dept;

    public Doctor(int empId, Employee.Role role, String empName, String password, boolean isActive, String contact, String email, String qualifications, String specialization) {
        super(empId, role, empName, password, isActive, contact, email);
        this.qualifications = qualifications;
        this.specialization = specialization;
        this.dept = Employee.Department.general;
    }

    public Doctor(int empId, Employee.Role role, String empName, String password, boolean isActive, String contact, String qualifications, String specialization) {
        super(empId, role, empName, password, isActive, contact);
        this.qualifications = qualifications;
        this.specialization = specialization;
        this.dept = Employee.Department.general;
    }

    public Doctor(int empId, Employee.Role role, String empName, String password, boolean isActive, String contact, String email, String qualifications, String specialization, Employee.Department dept) {
        super(empId, role, empName, password, isActive, contact, email);
        this.qualifications = qualifications;
        this.specialization = specialization;
        this.dept = dept;
    }

    public Doctor(int empId, Employee.Role role, String empName, String password, boolean isActive, String contact, String qualifications, String specialization, Employee.Department dept) {
        super(empId, role, empName, password, isActive, contact);
        this.qualifications = qualifications;
        this.specialization = specialization;
        this.dept = dept;
    }

    public Employee.Department getDept() {
        return dept;
    }

    public void setDept(Employee.Department dept) {
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
