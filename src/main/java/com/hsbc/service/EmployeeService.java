package com.hsbc.service;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.dao.EmployeeDao;
import com.hsbc.dao.EmployeeDaoImpl;
import com.hsbc.exceptions.*;
import com.hsbc.models.Doctor;
import com.hsbc.models.Employee;
import com.hsbc.models.User;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    public Employee employeeLogin(String email, String password) {
        EmployeeDao dao = new EmployeeDaoImpl();

        Employee emp ;
        try {
            emp = dao.employeeLogin(email, password);
            return emp;
        } catch(SQLException | EmployeeNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void getAllDoctors(){
        EmployeeDao dao = new EmployeeDaoImpl();
        List<Doctor> doctors =  null;

        try {
            doctors = dao.getAllDoctors();
            for(Doctor doctor: doctors) {
                System.out.println(doctor);
            }
        } catch(SQLException | NoDoctorsFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void addDoctor(String empName, String password, boolean isActive, String contact, String email, String qualifications, String specialization) {
        Doctor doctor = new Doctor(EmployeeEnums.Role.doctor, empName, password, isActive, contact, email, qualifications, specialization);
        EmployeeDao dao = new EmployeeDaoImpl();
        try{
            dao.addDoctor(doctor);
        } catch(SQLException | DoctorAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
    }

    public void addDoctor( String empName, String password, boolean isActive, String contact, String email, String qualifications, String specialization,EmployeeEnums.Department dept) {
        Doctor doctor = new Doctor(EmployeeEnums.Role.doctor, empName, password, isActive, contact, email, qualifications, specialization, dept);
        EmployeeDao dao = new EmployeeDaoImpl();

        try{
            dao.addDoctor(doctor);
        } catch(SQLException | DoctorAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeDoctor(int doctorId){
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.deleteEmployee(doctorId);
        } catch(SQLException | EmployeeNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateDoctor(){
        Doctor doctor = new Doctor();

        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.updateDoctor(doctor);
        } catch(SQLException | EmployeeNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void addUser(String userName, String password, String email, String contact, EmployeeEnums.Shift shift, EmployeeEnums.Department dept) {
        User user = new User(EmployeeEnums.Role.user, userName, password, true, contact, email, dept, shift);
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.addUser(user);
        } catch(SQLException | UserAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllUsers() {
        EmployeeDao dao = new EmployeeDaoImpl();
        List<User> users =  null;

        try {
            users = dao.getAllUsers();
            for(User user: users) {
                System.out.println(user);
            }

        } catch(SQLException | NoUsersFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeUser(int userId) {
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.deleteEmployee(userId);
        } catch(SQLException | EmployeeNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(){
        User user = new User();

        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.updateUser(user);
        } catch(SQLException | EmployeeNotFoundException e){
            System.out.println(e.getMessage());
        }
    }



    public void importUsers(String filepath){
        // Import users from a json file and then add them to the database

        File file = new File(filepath);
        EmployeeDao dao = new EmployeeDaoImpl();

        // Parse this file, validate file json format or xml format, and then store it in a List<User> then call Dao method

        // dao.addUsers();
    }

    public void getAllEmployees(){
        EmployeeDao dao = new EmployeeDaoImpl();
        List<Employee> employees =  null;

        try {
            employees = dao.getAllEmployees();
            for(Employee employee: employees) {
                System.out.println(employee);
            }
        } catch(SQLException | NoEmployeesFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void getEmployee(int empId){
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.getEmployee(empId);
        } catch(SQLException | EmployeeNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmployee(int empId){
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.deleteEmployee(empId);
        } catch(SQLException | EmployeeNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmployee(String email){
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.deleteEmployee(email);
        } catch(SQLException | EmployeeNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void addUsers(List<User> users){
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.addUsers(users);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void importDoctors(List<Doctor> doctors){
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.addDoctors(doctors);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void getDoctorReport(int doctorId){
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.getDoctorReport(doctorId);
        } catch(SQLException | EmployeeNotFoundException | NoRecordFoundException e){
            System.out.println(e.getMessage());
        }
    }

}
