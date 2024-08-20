package com.hsbc.dao;

import com.hsbc.exceptions.EmployeeNotFoundException;
import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.models.Employee;
import com.hsbc.models.Patient;

import java.sql.SQLException;

public interface AuthDao {
    public Employee employeeLogin(String empId, String password) throws EmployeeNotFoundException;
    public Boolean isEmployee(int id, String role);
    public Boolean isRegisteredPatient(int id);
}
