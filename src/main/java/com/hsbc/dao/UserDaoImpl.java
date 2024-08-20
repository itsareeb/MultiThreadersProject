package com.hsbc.dao;

import com.hsbc.exceptions.*;
import com.hsbc.utils.DBUtils;
import com.hsbc.models.Patient;
import com.hsbc.models.DoctorSchedule;
import com.hsbc.models.ShiftSlot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

public class UserDaoImpl implements UserDao {

    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public UserDaoImpl(Connection conn) {
        conn = DBUtils.getConnection();
    }

    @Override
    public void registerPatient(int uid, Patient patient) throws PatientAlreadyExistsException {

        String sql = "insert into patient values(?, ?, ?, ?, ?, ?, ?)";
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
    public void bookAppointment(DoctorSchedule schedule, ShiftSlot slot, int uid, int pid, int did, int shiftNumber,
            int slotNumber, Date dateofAppointment)
            throws AppointmentAlreadyExistsException, DoctorNotAvailableException, SlotNotAvailableException {
        // String sql_to_check = "select * from appointments where appointmentID = ?";

        // First we will check if appointment is already booked
        int count = Integer.parseInt(
                "select count(*) from appointments where doctorID = ? and date = ? and shiftNumber = ? and slotNumber = ?");
        try {
            ps = conn.prepareStatement(String.valueOf(count));
            ps.setInt(1, did);
            ps.setDate(2, dateofAppointment);
            ps.setInt(3, shiftNumber);
            ps.setInt(4, slotNumber);
            rs = ps.executeQuery();
            if (rs.getInt(1) > 0) {
                throw new AppointmentAlreadyExistsException("Appointment already exists");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Now whether schedule is available or not
        String schedule_sql = "select count(*) from schedule where doctorID = ? and date = ? and shift_number = ?";
        try {
            ps = conn.prepareStatement(schedule_sql);
            ps.setInt(1, did);
            ps.setDate(2, dateofAppointment);
            ps.setInt(3, shiftNumber);
            rs = ps.executeQuery();
            if (rs.getInt(1) == 0) {
                throw new DoctorNotAvailableException("Doctor is not available on this date");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Now whether slot is available or not
        String slot_sql = "select count(*) from slots where shift_number = ? and slot_number = ?";
        try {
            ps = conn.prepareStatement(slot_sql);
            ps.setInt(1, shiftNumber);
            ps.setInt(2, slotNumber);
            rs = ps.executeQuery();
            if (rs.getInt(1) == 0) {
                throw new SlotNotAvailableException("Slot is not available");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Now we will book the appointment
        String sql = "insert into appointments values(?, ?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(2, schedule.getDid());
            ps.setDate(6, schedule.getDate());
            ps.setInt(3, pid);
            ps.setInt(1, uid);
            ps.setInt(4, shiftNumber);
            ps.setInt(5, slotNumber);
            ps.executeUpdate();
            System.out.println("Appointment booked successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void registerPatient(Patient patient) throws PatientAlreadyExistsException {

    }

    @Override
    public void bookAppointment() throws AppointmentAlreadyExistsException {

    }

    @Override
    public void showAllPatients() throws NoPatientsFoundException {
        String sql = "select * from patient";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getInt(3) + " : "
                        + rs.getString(4) + " : " + rs.getString(5) + " : " + rs.getString(6));
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
                System.out.println(rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : "
                        + rs.getString(4) + " : " + rs.getString(5) + " : " + rs.getString(6));
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
