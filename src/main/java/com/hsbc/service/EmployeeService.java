package com.hsbc.service;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.dao.EmployeeDao;
import com.hsbc.dao.EmployeeDaoImpl;
import com.hsbc.exceptions.*;
import com.hsbc.models.*;
import com.hsbc.factory.EmployeeFactory;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    public Employee employeeLogin(String email, String password) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        Employee emp;
        try {
            emp = dao.employeeLogin(email, password);
            return emp;
        } catch (SQLException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void getAllDoctors() {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        List<Doctor> doctors = null;

        try {
            doctors = dao.getAllDoctors();
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        } catch (SQLException | NoDoctorsFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDoctor(String empName, String password, boolean isActive, String contact, String email,
            String qualifications, String specialization) {
        Doctor doctor = new Doctor(EmployeeEnums.Role.doctor, empName, password, isActive, contact, email,
                qualifications, specialization);
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.addDoctor(doctor);
        } catch (SQLException | DoctorAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDoctor(String empName, String password, boolean isActive, String contact, String email,
            String qualifications, String specialization, EmployeeEnums.Department dept) {
        Doctor doctor = new Doctor(EmployeeEnums.Role.doctor, empName, password, isActive, contact, email,
                qualifications, specialization, dept);
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();

        try {
            dao.addDoctor(doctor);
        } catch (SQLException | DoctorAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeDoctor(int doctorId) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.deleteEmployee(doctorId);
        } catch (SQLException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeDoctor(String email) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.deleteEmployee(email);
        } catch (SQLException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateDoctor(Doctor doctor) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.updateDoctor(doctor);
        } catch (SQLException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addUser(String userName, String password, String email, String contact, EmployeeEnums.Shift shift,
            EmployeeEnums.Department dept) {
        User user = new User(EmployeeEnums.Role.user, userName, password, true, contact, email, dept, shift);
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.addUser(user);
        } catch (SQLException | UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllUsers() {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        List<User> users = null;

        try {
            users = dao.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }

        } catch (SQLException | NoUsersFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUser(int userId) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.deleteEmployee(userId);
        } catch (SQLException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUser(String email) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.deleteEmployee(email);
        } catch (SQLException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(User user) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.updateUser(user);
        } catch (SQLException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void importUsers(List<User> users) {

        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();

        try {
            dao.addUsers(users);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllEmployees() {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        List<Employee> employees = null;

        try {
            employees = dao.getAllEmployees();
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } catch (SQLException | NoEmployeesFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getEmployee(int empId) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.getEmployee(empId);
        } catch (SQLException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmployee(int empId) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.deleteEmployee(empId);
        } catch (SQLException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmployee(String email) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.deleteEmployee(email);
        } catch (SQLException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addUsers(List<User> users) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.addUsers(users);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void importDoctors(List<Doctor> doctors) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            dao.addDoctors(doctors);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getDoctorReport(int doctorId) {
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            DoctorReport report = dao.getDoctorReport(doctorId);
            System.out.println(report);
        } catch (SQLException | EmployeeNotFoundException | NoRecordFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getUserReport(int userId){
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            UserReport report = dao.getUserReport(userId);
            System.out.println(report);
        } catch (SQLException | EmployeeNotFoundException | NoRecordFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getDoctorDetails(int empId){
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            Doctor doctor = dao.getDoctor(empId);
            System.out.println(doctor);
        } catch (SQLException | DoctorNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getUserDetails(int empId){
        EmployeeDao dao = new EmployeeFactory().getEmployeeDao();
        try {
            User user = dao.getUser(empId);
            System.out.println(user);
        } catch (SQLException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
