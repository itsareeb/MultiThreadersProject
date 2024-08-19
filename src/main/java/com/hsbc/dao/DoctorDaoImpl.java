package com.hsbc.dao;


import com.hsbc.models.Appointment;
import com.hsbc.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import static com.hsbc.utils.DBUtils.conn;

public class DoctorDaoImpl implements DoctorDao {

    Connection conn;

    public DoctorDaoImpl() {
        conn = DBUtils.getConnection();
        //System.out.println(conn);
    }

    public static void main(String[] args) {
        DoctorDaoImpl dao = new DoctorDaoImpl();
        //System.out.println(dao.viewAppointements(305));
        //dao.suggestMedicalTest(6, "Dolo", "250mg", "after food");
        dao.suggestMedicalTest(5, "MRI Scan");
    }

    public List<Appointment> viewAppointements(int id) {
        List<Appointment> appointments = new ArrayList<Appointment>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from appointments where doctorID = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppId(rs.getInt("appointmentID"));
                appointment.setDate(rs.getDate("date"));
                appointment.setDid(rs.getInt("doctorID"));
                appointment.setUid(rs.getInt("userID"));
                appointments.add(appointment);

                //System.out.println(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointments;
    }

    //Function to suggest medicines
    public void suggestMedicalTest(int appointmentID, String medicineName, String dosage, String instruction) {
        String sql = "INSERT INTO Medications (appointmentID, name, dosage, instructions) VALUES(?,?,?,?)";
        try {
            Statement stmt = conn.createStatement();
            //ResultSet rs =stmt.executeQuery(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, appointmentID);
            ps.setString(2, medicineName);
            ps.setString(3, dosage);
            ps.setString(4, instruction);
            ps.executeUpdate();
            System.out.println("Medicine Suggested");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void suggestMedicalTest(int appointmentID, String testName) {
        String sql = "INSERT INTO tests (appointmentID, name) VALUES(?,?)";
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, appointmentID);
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
