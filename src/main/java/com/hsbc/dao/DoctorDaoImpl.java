package com.hsbc.dao;


import com.hsbc.exceptions.AppointmentNotFoundException;
import com.hsbc.exceptions.NoAppointmentsFoundException;
import com.hsbc.models.Appointment;
import com.hsbc.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DoctorDaoImpl implements DoctorDao {

    Connection conn;

    public DoctorDaoImpl() {
        conn = DBUtils.getConnection();
        //System.out.println(conn);
    }

    public static void main(String[] args) {
        DoctorDaoImpl dao = new DoctorDaoImpl();
        try{
            dao.suggestMedications(6, "Dolo", "250mg", "after food");
        } catch (AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try{
            dao.suggestMedicalTest(5, "MRI Scan");
        } catch (AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Appointment> viewAppointments(int id) throws NoAppointmentsFoundException {
        List<Appointment> appointments = new ArrayList<Appointment>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from appointments where doctorID = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(!rs.next()) {
                throw new NoAppointmentsFoundException("No appointments found");
            }

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appointmentID"));
                appointment.setDate(rs.getDate("date"));
                appointment.setScheduleId(rs.getInt("doctorID"));
                appointment.setUserId(rs.getInt("userID"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointments;
    }

    //Function to suggest medicines
    public void suggestMedications(int appointmentId, String medicineName, String dosage, String instruction) throws AppointmentNotFoundException {

        AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
        if(!appointmentDao.isAppointmentExist(appointmentId)) {
            throw new AppointmentNotFoundException("Appointment not found");
        }

        String sql = "INSERT INTO Medications (appointmentId, name, dosage, instructions) VALUES(?,?,?,?)";
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ps.setString(2, medicineName);
            ps.setString(3, dosage);
            ps.setString(4, instruction);
            ps.executeUpdate();
            System.out.println("Medicine Suggested");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void suggestMedicalTest(int appointmentId, String testName) throws AppointmentNotFoundException {
        AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
        if(!appointmentDao.isAppointmentExist(appointmentId)) {
            throw new AppointmentNotFoundException("Appointment not found");
        }
        String sql = "INSERT INTO tests (appointmentId, name) VALUES(?,?)";
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ps.setString(2, testName);
            ps.executeUpdate();
            System.out.println("Medical Test Suggested");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSchedule() {

    }

    public void updateSchedule() {

    }
}
