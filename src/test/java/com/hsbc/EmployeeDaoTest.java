package com.hsbc;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.dao.EmployeeDaoImpl;
import com.hsbc.exceptions.EmployeeNotFoundException;
import com.hsbc.exceptions.NoRecordFoundException;
import com.hsbc.models.Doctor;
import com.hsbc.models.Employee;
import com.hsbc.models.User;
import com.hsbc.utils.DBUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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


    //Test Cases for addDoctor
    @Test
    public void addDoctorTest1() throws SQLException, EmployeeNotFoundException {
        Doctor doctor= new Doctor(EmployeeEnums.Role.doctor,"Dr.Bhanu","Password",true,"7777777777","bhanu@gmail.com","MBBS FRCS","Dentist");
        //Employee doctor = new Doctor(EmployeeEnums.Role.doctor,"Dr.Bhanu","Password",true,"7777777777","bhanu@gmail.com","MBBS FRCS","Dentist");
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        try {
            employeeDao.addDoctor(doctor);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(employeeDao.getEmployee("bhanu@gmail.com"));
    }

    @Test(expected = RuntimeException.class)
    public void addDoctorDuplicateEntryTest() throws SQLException, EmployeeNotFoundException {
        Doctor doctor= new Doctor(EmployeeEnums.Role.doctor,"Dr.Bhanu","Password",true,"7777777777","bhanu@gmail.com","MBBS FRCS","Dentist");
        //Employee doctor = new Doctor(EmployeeEnums.Role.doctor,"Dr.Bhanu","Password",true,"7777777777","bhanu@gmail.com","MBBS FRCS","Dentist");
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        try {
            employeeDao.addDoctor(doctor);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Test Cases for addUser Function
    @Test
    public void addUserTest1() throws SQLException, EmployeeNotFoundException {
        User user = new User(EmployeeEnums.Role.user,"Userxyz","Password@123",true,"999999999","userxyz@example.com", EmployeeEnums.Department.general, EmployeeEnums.Shift.night);
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.addUser(user);
        assertNotNull(employeeDao.getEmployee("userxyz@example.com"));

    }

    @Test(expected = SQLException.class)
    public void addUserDuplicateEntryTest() throws SQLException {
        User user = new User(EmployeeEnums.Role.user,"Userxyz","Password@123",true,"999999999","userxyz@example.com", EmployeeEnums.Department.general, EmployeeEnums.Shift.night);
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.addUser(user);
    }

    //Test Cases For Update Doctor:
    @Test
    public void updateDoctorTestCase1() throws SQLException, EmployeeNotFoundException, NoRecordFoundException {
        Doctor doctor= new Doctor(EmployeeEnums.Role.doctor,"Dr.Bhanu","Password",true,"7777777777","bhanu@gmail.com","MBBS M.D","Dentist");
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.updateDoctor(doctor);
        assertEquals("MBBS M.D", employeeDao.getDoctorReport(7).getQualifications());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void updateDoctorTestForInvalidDoctor() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Doctor doctor= new Doctor(EmployeeEnums.Role.doctor,"Dr.JamesCameron","Password",true,"7777777777","jamesCamron@gmail.com","MBBS M.D","Dentist");
        employeeDao.updateDoctor(doctor);
    }

    //Test Cases For Update User
    @Test
    public void updateUserTestCase1() throws SQLException, EmployeeNotFoundException, NoRecordFoundException {
        User user = new User(EmployeeEnums.Role.user,"Userxyz","Password@123",true,"999999999","userxyz@example.com", EmployeeEnums.Department.general, EmployeeEnums.Shift.day);
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.updateUser(user);
        assertEquals(employeeDao.getUserReport(11).getShift(), EmployeeEnums.Shift.day);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void updateUserTestCaseForInvalidUser() throws SQLException, EmployeeNotFoundException {
        User user = new User(EmployeeEnums.Role.user,"Meow","Password@123",true,"9999888899","meow@example.com", EmployeeEnums.Department.general, EmployeeEnums.Shift.day);
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.updateUser(user);
    }

    //Test Cases For deleteEmployeeFunction
    @Test
    public void deleteEmployeeTestCase1() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertTrue(employeeDao.deleteEmployee(11));
    }

    @Test
    public void deleteEmployeeTestCaseForInvalidEmployee() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        assertFalse(employeeDao.deleteEmployee(22));
    }

    //Test Cases for getEmployee
    @Test
    public void getEmployeeTestCase1() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee employee = employeeDao.getEmployee(11);
        assertNotNull(employee);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void getEmployeeTestCaseForInvalidEmployee() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee employee = employeeDao.getEmployee(22);
    }

    //Test Cases For getEmployee function - email as input
    @Test
    public void getEmployeeTestCaseEmail() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee employee = employeeDao.getEmployee("jane.smith@example.com");
        assertNotNull(employee);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void getEmployeeTestCaseForInvalidEmployeeEmail() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee employee = employeeDao.getEmployee("abcxyz@gmail.com");
    }


    //TestCases For getAllEmployees()
    @Test
    public void getAllEmployeesTestCase1() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        List<Employee> employees = employeeDao.getAllEmployees();
        assertNotNull(employees);
    }

    @Test
    public void getAllDoctorsTestCase1() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        List<Doctor> doctors = employeeDao.getAllDoctors();
        assertNotNull(doctors);
    }

    @Test
    public void getAllUsersTestCase1() throws SQLException, EmployeeNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        List<User> users = employeeDao.getAllUsers();
        assertNotNull(users);
    }


































}
