package com.hsbc.dao;

import com.hsbc.exceptions.AppointmentNotFoundException;
import com.hsbc.models.Appointment;
import com.hsbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public AppointmentDaoImpl() {
        conn = DBUtils.getConnection();
    }

    @Override
    public boolean isAppointmentExist(int appointmentId) {
        String sql = "select count(*) from appointments where appointmentId = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }



    @Override
    public Appointment getAppointment(int appointmentId) throws SQLException, AppointmentNotFoundException {
        return null;
    }

    @Override
    public void addAppointment(Appointment appointment) throws SQLException {

    }

    @Override
    public void updateAppointment(Appointment appointment) throws SQLException {

    }

    @Override
    public void deleteAppointment(int appointmentId) throws SQLException {

    }

    @Override
    public List<Appointment> getAllAppointments(LocalDate date) throws SQLException {
        return Arrays.asList();
    }

    @Override
    public List<Appointment> getAllAppointments(int doctotId, LocalDate date) throws SQLException {
        return Arrays.asList();
    }

    @Override
    public List<Appointment> getAllAppointments() throws SQLException {
        return Arrays.asList();
    }
}
