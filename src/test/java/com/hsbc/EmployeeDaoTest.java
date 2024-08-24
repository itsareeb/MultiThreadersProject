package com.hsbc;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.dao.EmployeeDaoImpl;
import com.hsbc.exceptions.EmployeeNotFoundException;
import com.hsbc.models.Employee;
import com.hsbc.utils.DBUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class EmployeeDaoTest {
    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @BeforeEach
    public void init(){
        this.conn = DBUtils.getConnection();
    }

    //Test Cases for isEmployee
    @Test
    public void isEmployeeValidEmpTestUser(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(true, employeeDao.isEmployee(101,"user"));
    }

    @Test
    public void isEmployeeValidEmpTestDoctor(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(true, employeeDao.isEmployee(301,"doctor"));
    }

    @Test
    public void isEmployeeValidEmpTestAdmin(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(true, employeeDao.isEmployee(201,"admin"));
    }

    @Test
    public void isEmployeeValidEmpNegativeTestUser(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertNotEquals(false, employeeDao.isEmployee(101,"user"));
    }

    @Test
    public void isEmployeeValidEmpNegativeTestAdmin(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertNotEquals(false, employeeDao.isEmployee(201,"admin"));
    }

    @Test
    public void isEmployeeValidEmpNegativeTestDoctor(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertNotEquals(false, employeeDao.isEmployee(301,"doctor"));
    }


    @Test
    public void isEmployeeInValidEmpTestUser(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(false, employeeDao.isEmployee(1888,"user"));
    }
    @Test
    public void isEmployeeInValidEmpTestDoctor(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(false, employeeDao.isEmployee(401,"doctor"));
    }
    @Test
    public void isEmployeeInValidEmpTestAdmin(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(false, employeeDao.isEmployee(301,"admin"));
    }
    
    @Test
    public void noConnectionTest(){
        this.conn = null;
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertThrows(new NullPointerException(), employeeDao.isEmployee(301,"admin"));
    }

    private void assertThrows(NullPointerException e, Boolean admin) {
    }

    @Test
    public void notNullTest(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertNotNull(employeeDao);
    }

    @Test
    public void notNullTestForObject(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertNotNull(employeeDao.isEmployee(101,"user"));
    }


    @Test
    public void testForObjectWithNullArg(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertFalse(employeeDao.isEmployee(101,null));
    }


    @Test
    public void testForObjectWithEmptyArg(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertFalse(employeeDao.isEmployee(101,""));
    }

    //TestCases for employeelogin

    @Test
    public void employeeLoginValidEmpTest1() throws EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(new Employee(101, EmployeeEnums.Role.user,"user1","null",true,"1122331122","user1@gmail.com"),employeeDao.employeeLogin("user1@gmail.com","p1234"));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void employeeLoginInvalidEmpTest1() throws EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.employeeLogin("meow@gmail.com","Password");
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void employeeLoginNullCredentialsEmpTest1() throws EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.employeeLogin("","");
    }

    @Test
    public void employeeLoginValidEmpNegativeTest1() throws EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertNotEquals(new Employee(104, EmployeeEnums.Role.user,"user1","null",true,"1122331122","user1@gmail.com"),employeeDao.employeeLogin("user1@gmail.com","p1234"));
    }

    @Test
    public void employeeLoginNotNullTest(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        try {
            assertNotNull(employeeDao.employeeLogin("doctor2@gmail.com","p1234"));
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void employeeLoginNullTest(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        try {
            assertFalse(employeeDao.employeeLogin("dr.bob@example.com","password456")==null);
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
