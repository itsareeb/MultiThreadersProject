package com.hsbc;


import com.hsbc.dao.AppointmentDaoImpl;
import com.hsbc.utils.DBUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import com.hsbc.exceptions.*;
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

import static org.junit.Assert.*;

public class AdminDaoTest {
    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @BeforeEach
    public void init(){
        this.conn = DBUtils.getConnection();
    }


    //Test Cases for getAppointment
    @Test
    public void getAppointmentTestCase1(){
        AppointmentDaoImpl appointmentDao= new AppointmentDaoImpl();
        try {
            assertNotNull(appointmentDao.getAppointment(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected= RuntimeException.class)
    public void getAppointmentTestCaseForInvalidAppId(){
        AppointmentDaoImpl appointmentDao= new AppointmentDaoImpl();
        try {
            appointmentDao.getAppointment(101);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Test Cases to check isSlotAvailable
    @Test
    public void isSlotAvailableTest() throws SQLException {
        AppointmentDaoImpl appointmentDao= new AppointmentDaoImpl();
        assertFalse(appointmentDao.isSlotAvailable(5,1));
    }

    @Test
        public void isSlotAvailableTest2() throws SQLException {
        AppointmentDaoImpl appointmentDao= new AppointmentDaoImpl();
        assertFalse(appointmentDao.isSlotAvailable(8,1));
    }

}
