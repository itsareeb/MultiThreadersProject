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
import java.sql.SQLException;

import static org.junit.Assert.*;

public class EmployeeDaoTest {
    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @BeforeEach
    public void init(){
        this.conn = DBUtils.getConnection();
    }

    //Test Cases for isValidEmployee
    @Test
    public void isValidEmployeeTestForUser(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(true, employeeDao.isValidEmployee(3, EmployeeEnums.Role.valueOf("user")));
    }

    @Test
    public void isValidEmployeeTestForAdmin(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(true, employeeDao.isValidEmployee(1, EmployeeEnums.Role.valueOf("doctor")));
    }

    @Test
    public void isValidEmployeeTestForDoctor(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(true, employeeDao.isValidEmployee(5, EmployeeEnums.Role.valueOf("admin")));
    }

    @Test
    public void isValidEmployeeTestForInvalidDoctor(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(false, employeeDao.isValidEmployee(6, EmployeeEnums.Role.valueOf("doctor")));
    }

    @Test
    public void isValidEmployeeTestForInvalidUser(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(false, employeeDao.isValidEmployee(6, EmployeeEnums.Role.valueOf("user")));
    }

    @Test
    public void isValidEmployeeTestForInvalidAdmin(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertEquals(false, employeeDao.isValidEmployee(6, EmployeeEnums.Role.valueOf("admin")));
    }

    @Test
    public void isValidEmployeeFalseTestForDoctor(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertFalse(employeeDao.isValidEmployee(6, EmployeeEnums.Role.valueOf("admin")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSQLInjectionOrCondition() {
        // Given
        int empId = 1;
        String role = "admin' OR '1'='1";
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.isValidEmployee(empId, EmployeeEnums.Role.valueOf(role));
    }

    @Test(expected = NumberFormatException.class)
    public void testSQLInjectionOrConditionOnEmpId() {
        // Given
        String empId = "1 OR 1=1";
        EmployeeEnums.Role role = EmployeeEnums.Role.valueOf("admin");

        // When & Then
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.isValidEmployee(Integer.parseInt(empId),role);
    }



    //Tests for employeeLogin

    @Test
    public void employeeLoginTestForValidEmployee(){
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee employee = new Employee(1,EmployeeEnums.Role.doctor, "Dr. John Doe","null",true,"9876543210","john.doe@example.com");
        try {
            assertEquals(employeeDao.employeeLogin("john.doe@example.com","password123"), employee);
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void employeeLoginTestForInValidEmail() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.employeeLogin("john.dairy@example.com","password123");
    }
    @Test(expected = EmployeeNotFoundException.class)
    public void employeeLoginTestForInvalidPassword() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.employeeLogin("john.doe@example.com","password12345");
    }

    @Test
    public void testSQLInjectionOrConditionInEmail() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com' OR '1'='1";
        String password = "password12345";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionOrConditionInPassword() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com";
        String password = "password12345' OR '1'='1";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionCommentInEmail() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com' --";
        String password = "password12345";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionCommentInPassword() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com";
        String password = "password12345' --";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionAlwaysTrueConditionInEmail() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com' OR 'a'='a";
        String password = "password12345";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionUnionSelectInEmail() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com' UNION SELECT NULL, NULL, NULL --";
        String password = "password12345";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionUnionSelectInPassword() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com";
        String password = "password12345' UNION SELECT NULL, NULL, NULL --";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionSubqueryInEmail() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com'; SELECT * FROM Employee WHERE 'a'='a";
        String password = "password12345";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionSubqueryInPassword() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com";
        String password = "password12345'; SELECT * FROM Employee WHERE 'a'='a";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionInvalidBooleanLogicInEmail() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com' AND '1'='2";
        String password = "password12345";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionInvalidBooleanLogicInPassword() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com";
        String password = "password12345' AND '1'='2";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionTimingAttackInEmail() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com' AND SLEEP(5) --";
        String password = "password12345";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }

    @Test
    public void testSQLInjectionTimingAttackInPassword() {
        // Given
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String email = "john.doe@example.com";
        String password = "password12345' AND SLEEP(5) --";

        // When & Then
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeDao.employeeLogin(email, password);
        });
    }






















}
