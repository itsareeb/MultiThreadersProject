package com.hsbc.dao;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.exceptions.EmployeeNotFoundException;
import com.hsbc.models.Doctor;
import com.hsbc.models.Employee;
import com.hsbc.models.User;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    public Employee employeeLogin(String email, String password) throws EmployeeNotFoundException, SQLException;
    public Boolean isValidEmployee(int empId, EmployeeEnums.Role role) throws SQLException;
    public Boolean isValidEmployee(String email, EmployeeEnums.Role role) throws SQLException;
    public Employee addEmployee(Doctor doctor) throws SQLException;
    public Employee addEmployee(User user) throws SQLException;
    public Employee updateEmployee(Doctor doctor) throws SQLException, EmployeeNotFoundException;
    public Employee updateEmployee(User user) throws SQLException, EmployeeNotFoundException;
    public boolean deleteEmployee(int empId) throws SQLException;
    public boolean deleteEmployee(String email) throws SQLException;



    public Employee getEmployee(int empId) throws SQLException, EmployeeNotFoundException;
    public Employee getEmployee(String email) throws SQLException, EmployeeNotFoundException;
    public List<Employee> getAllEmployees() throws SQLException;
    public List<Doctor> getAllDoctors() throws SQLException;
    public List<User> getAllUsers() throws SQLException;
}
