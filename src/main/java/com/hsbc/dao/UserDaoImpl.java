package com.hsbc.dao;

import com.hsbc.exceptions.AppointmentAlreadyExistsException;
import com.hsbc.exceptions.NoAppointmentsFoundException;
import com.hsbc.exceptions.NoDoctorsFoundException;
import com.hsbc.exceptions.NoPatientsFoundException;
import com.hsbc.exceptions.PatientAlreadyExistsException;
import com.hsbc.utils.DBUtils;
import com.hsbc.models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public UserDaoImpl(Connection conn) {
        conn = DBUtils.getConnection();
    }

    @Override
    public void registerPatient(int uid, Patient patient) throws PatientAlreadyExistsException {

        String sql = "insert into patient values(?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, patient.getPid());
            ps.setString(2, patient.getPname());
            ps.setInt(3, patient.getAge());
            ps.setString(4, String.valueOf(patient.getGender()));
            ps.setString(5, patient.getContact());
            ps.setString(6, patient.getEmail());
            ps.setInt(7, uid);

            ps.executeUpdate();
            System.out.println("Patient registered successfully");
        } catch (Exception e) {
            throw new PatientAlreadyExistsException("Patient already exists");
        } finally {
            try {

                ps.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void bookAppointment() throws AppointmentAlreadyExistsException {
        String sql_to_check = "select * from appointments where appointmentID = ?";


    }

    @Override
    public void showAllPatients() throws NoPatientsFoundException {
        String sql = "select * from patient";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getInt(3) + " : " + rs.getString(4) + " : " + rs.getString(5) + " : " + rs.getString(6));
            }
        } catch (Exception e) {
            throw new NoPatientsFoundException("No patients found");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void viewAppointments() throws NoAppointmentsFoundException {
        String sql = "select * from appointments";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " : " + rs.getDate(2) + " : " + rs.getInt(3) + " : " + rs.getInt(4));
            }
        } catch (Exception e) {
            throw new NoAppointmentsFoundException("No appointments found");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void showAllDoctors() throws NoDoctorsFoundException {
        String sql = "select * from doctor";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getString(4) + " : " + rs.getString(5) + " : " + rs.getString(6));
            }
        } catch (Exception e) {
            throw new NoDoctorsFoundException("No doctors found");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
