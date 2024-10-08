package com.hsbc.dao;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.exceptions.*;
import com.hsbc.models.*;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    public User getUser(int empId) throws SQLException, UserNotFoundException;
    public Doctor getDoctor(int empId) throws SQLException, DoctorNotFoundException;
    public Employee employeeLogin(String email, String password) throws EmployeeNotFoundException, SQLException;
    public Boolean isValidEmployee(int empId, EmployeeEnums.Role role) throws SQLException;
    public Boolean isValidEmployee(String email, EmployeeEnums.Role role) throws SQLException;
    public Employee addDoctor(Doctor doctor) throws SQLException, DoctorAlreadyExistsException;
    public Employee addUser(User user) throws SQLException, UserAlreadyExistsException;
    public Employee updateDoctor(Doctor doctor) throws SQLException, EmployeeNotFoundException;
    public Employee updateUser(User user) throws SQLException, EmployeeNotFoundException;
    public boolean deleteEmployee(int empId) throws SQLException, EmployeeNotFoundException;
    public boolean deleteEmployee(String email) throws SQLException, EmployeeNotFoundException;

    public void addDoctors(List<Doctor> doctors) throws SQLException;
    public void addUsers(List<User> users) throws SQLException;

    public Employee getEmployee(int empId) throws SQLException, EmployeeNotFoundException;
    public Employee getEmployee(String email) throws SQLException, EmployeeNotFoundException;
    public List<Employee> getAllEmployees() throws SQLException, NoEmployeesFoundException;
    public List<Doctor> getAllDoctors() throws SQLException,NoDoctorsFoundException;
    public List<User> getAllUsers() throws SQLException,NoUsersFoundException;
    public DoctorReport getDoctorReport(int doctorId) throws SQLException, EmployeeNotFoundException, NoRecordFoundException;
    public UserReport getUserReport(int userId) throws SQLException, EmployeeNotFoundException, NoRecordFoundException;
}
