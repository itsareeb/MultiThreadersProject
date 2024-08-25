package com.hsbc;

import com.hsbc.Enums.PatientEnums;
import com.hsbc.dao.PatientDao;
import com.hsbc.dao.PatientDaoImpl;
import com.hsbc.exceptions.PatientAlreadyExistsException;
import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.models.Patient;
import com.hsbc.utils.DBUtils;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDaoTest {
    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @BeforeEach
    public void init() {
        this.conn = DBUtils.getConnection();
    }

    @AfterEach
    public void destroy() throws SQLException {
        ps.close();
    }

    //Test cases for isPatientRegistered Function
    @Test
    public void isPatientRegisteredTest() throws SQLException {
        PatientDao patientDao = new PatientDaoImpl();
        assertEquals(true, patientDao.isRegisteredPatient(1));
    }

    @Test
    public void isPatientRegisteredTest2() throws SQLException {
        PatientDao patientDao = new PatientDaoImpl();
        assertEquals(true, patientDao.isRegisteredPatient(2));
    }


    @Test
    public void testIsPatientRegisteredInValidUser1(){
        PatientDao patientDao = new PatientDaoImpl();
        try {
            assertEquals(false, patientDao.isRegisteredPatient(408));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void negativeTestIsPatientRegisteredInValidUser1(){
        PatientDao patientDao = new PatientDaoImpl();
        try {
            assertNotEquals(true, patientDao.isRegisteredPatient(105));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void FalseTestIsPatientRegisteredInValidUser1(){
        PatientDao patientDao = new PatientDaoImpl();
        try {
            assertFalse(patientDao.isRegisteredPatient(409));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testIsPatientRegisteredInValidUser2(){
        PatientDao patientDao = new PatientDaoImpl();
        try {
            assertEquals(false, patientDao.isRegisteredPatient(410));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Test Cases for isPatientRegistered : input- name, contact
    @Test
    public void getPatientTestForValidPatient() throws SQLException, PatientNotFoundException {
        PatientDao patientDao = new PatientDaoImpl();
        Patient patient = new Patient(2, "Bob White",PatientEnums.Gender.valueOf("male"),30,"9998887775","bob.white@example.com", 4);
        assertEquals(patient, patientDao.getPatient("Bob White","9998887775"));
    }

    @Test
    public void getPatientTestForInValidPatient() throws SQLException, PatientNotFoundException {
        PatientDao patientDao = new PatientDaoImpl();
        assertThrows(PatientNotFoundException.class, () -> {patientDao.getPatient("Mark","1231231230");});
    }

    //Test cases for addPatient function:
    @Test
    public void addPatientTestForValidPatient() throws SQLException, PatientNotFoundException, PatientAlreadyExistsException {
        Patient patient= new Patient("Ramu", PatientEnums.Gender.male,43,"7474747474",4);
        PatientDao patientDao = new PatientDaoImpl();
        patientDao.addPatient(patient);
        assertNotNull(patientDao.getPatient("Ramu","7474747474"));
    }

    @Test(expected = PatientAlreadyExistsException.class)
    public void addPatientTestForExistingPatient() throws SQLException, PatientNotFoundException, PatientAlreadyExistsException {
        PatientDao patientDao = new PatientDaoImpl();
        Patient patient= new Patient("Ramu", PatientEnums.Gender.male,43,"7474747474",4);
        patientDao.addPatient(patient);
    }

    //Test Cases for UpdatePatient
    @Test
    public void updatePatientTestForValidPatient() throws SQLException, PatientNotFoundException, PatientAlreadyExistsException {
        PatientDao patientDao = new PatientDaoImpl();
        Patient patient= new Patient("Ramu", PatientEnums.Gender.male,50,"7474747474",4);
        patientDao.updatePatient(patient);
        assertEquals(50,patientDao.getPatient("Ramu","7474747474").getAge());

    }

    @Test(expected = PatientNotFoundException.class)
    public void updatePatientTestForInValidPatient() throws SQLException, PatientNotFoundException, PatientAlreadyExistsException {
        PatientDao patientDao = new PatientDaoImpl();
        Patient patient= new Patient("Jackie Chan", PatientEnums.Gender.male,50,"2222222222",4);
        patientDao.updatePatient(patient);
    }

    @Test
    public void updatePatientTestForValidPatient2() throws SQLException, PatientNotFoundException, PatientAlreadyExistsException {
        PatientDao patientDao = new PatientDaoImpl();
        Patient patient= new Patient("Ramu", PatientEnums.Gender.male,50,"7474747474",4);
        assertEquals(true, patientDao.updatePatient(patient));
    }

    //Test Cases for getAllPatients()
    @Test
    public void getAllPatientsTestNotNull() throws SQLException {
        PatientDao patientDao = new PatientDaoImpl();
        assertNotNull(patientDao.getAllPatients());
    }

    @Test
    public void getAllPatientsTestCountRows() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Patient";
        int count=0;
        this.conn = DBUtils.getConnection();

        try (
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve and display the count
            if (resultSet.next()) {
                count = resultSet.getInt(1);  // Get the first column of the result set

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PatientDao patientDao = new PatientDaoImpl();
        assertEquals(count, patientDao.getAllPatients().size() );

    }

    //TestCases to get Patient Report
    @Test
    public void getPatientReportTest() throws SQLException, PatientNotFoundException {
        PatientDao patientDao = new PatientDaoImpl();
        assertNotNull(patientDao.getPatientReport(5));
    }
















}

