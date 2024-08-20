package com.hsbc.dao;

import com.hsbc.exceptions.EmployeeNotFoundException;
import com.hsbc.models.Employee;

public interface EmployeeDao {
    public Employee employeeLogin(String empId, String password) throws EmployeeNotFoundException;
    public Boolean isEmployee(int id, String role);
}
