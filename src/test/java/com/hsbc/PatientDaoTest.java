package com.hsbc;

import com.hsbc.Enums.PatientEnums;
import com.hsbc.dao.PatientDao;
import com.hsbc.dao.PatientDaoImpl;
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
    








}

