package main.java.co.hsbc.models;

public class Employee {
    int empId;
    enum Role {
            user,
            admin,
            doctor
    };
    Role role;
    String empName;
    String password;
    boolean isActive;
    String contact;
    String email;

    public Employee() {
    }

    public Employee(int empId, Role role, String empName, String password, boolean isActive, String contact, String email) {
        this.empId = empId;
        this.role = role;
        this.empName = empName;
        this.password = password;
        this.isActive = isActive;
        this.contact = contact;
        this.email = email;
    }

}
