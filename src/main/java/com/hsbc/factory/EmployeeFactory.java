package com.hsbc.factory;

import com.hsbc.dao.EmployeeDao;
import com.hsbc.dao.EmployeeDaoImpl;

public class EmployeeFactory {
    EmployeeDao employeeDao;
    public EmployeeFactory() {
        employeeDao = new EmployeeDaoImpl();
    }
    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }
}
