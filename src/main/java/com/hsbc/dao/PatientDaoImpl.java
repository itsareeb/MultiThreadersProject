package com.hsbc.dao;

import com.hsbc.Enums.AppointmentEnums;
import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.Enums.PatientEnums;
import com.hsbc.exceptions.PatientAlreadyExistsException;
import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.exceptions.UserNotFoundException;
import com.hsbc.models.Appointment;
import com.hsbc.models.AppointmentReport;
import com.hsbc.models.Patient;
import com.hsbc.models.PatientReport;
import com.hsbc.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PatientDaoImpl implements PatientDao {
    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public PatientDaoImpl() {
        this.conn = DBUtils.getConnection();
    }

    @Override
    public Boolean isRegisteredPatient(int id) throws SQLException{
        String query = "SELECT * FROM Patient WHERE patientID=?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Boolean isRegisteredPatient(String name, String contact) throws SQLException{
        String query = "SELECT * FROM Patient WHERE patientName=? AND contact=?";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, contact);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Patient getPatient(String name, String contact) throws PatientNotFoundException, SQLException {
        String query = "SELECT * FROM Patient WHERE patientName=? AND contact=?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, contact);
            rs = ps.executeQuery();
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setPid(rs.getInt("patientID"));
                patient.setPname(rs.getString("patientName"));
                patient.setContact(rs.getString("contact"));
                patient.setGender(PatientEnums.Gender.valueOf(rs.getString("gender")));
                patient.setAge(rs.getInt("age"));
                patient.setEmail(rs.getString("email"));
                patient.setUid(rs.getInt("userID"));
                return patient;
            }
            else{
                throw new PatientNotFoundException("Patient doesn't exist");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }


    @Override
    public void addPatient(Patient patient) throws PatientAlreadyExistsException,  SQLException {
        if (isRegisteredPatient(patient.getPname(), patient.getContact())){
            throw new PatientAlreadyExistsException("Patient already exists");
        }
        System.out.println(patient);
        String sql = "insert into patient (patientName, gender, age, contact, email, userId) values(?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, patient.getPname());
            ps.setString(2, String.valueOf(patient.getGender()));
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getContact());
            ps.setString(5, patient.getEmail());
            ps.setInt(6, patient.getUid());
            ps.executeUpdate();
            System.out.println("Patient registered successfully");
        }

    }


    @Override
    public boolean updatePatient(Patient patient) throws SQLException, PatientNotFoundException {
        if (!isRegisteredPatient(patient.getPname(), patient.getContact())){
            throw new PatientNotFoundException("Patient doesn't exist");
        }
        String updateQuery = "UPDATE Patient SET patientName = ?, age = ?, contact = ?, email = ? WHERE patientName=? AND contact = ?";
        try(PreparedStatement ps = conn.prepareStatement(updateQuery)) {
            ps.setString(1, patient.getPname());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getContact());
            ps.setString(4, patient.getEmail());
            ps.setString(5, patient.getPname());
            ps.setString(6, patient.getContact());
            ps.executeUpdate();
            return true;
        }
    }

    @Override
    public List<Patient> getAllPatients() throws SQLException {
        String query = "SELECT * FROM Patient";
        List<Patient> patientList = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPid(rs.getInt("patientId"));
                patient.setPname(rs.getString("patientName"));
                patient.setGender(PatientEnums.Gender.valueOf(rs.getString("gender")));
                patient.setAge(rs.getInt("age"));
                patient.setContact(rs.getString("contact"));
                patient.setEmail(rs.getString("email"));
                patient.setUid(rs.getInt("userId"));
                patientList.add(patient);
            }
            return patientList;
        }
    }

    @Override
    public PatientReport getPatientReport(int patientId) throws SQLException, PatientNotFoundException {
        if (!isRegisteredPatient(patientId)){
            throw new PatientNotFoundException("Report cant be generated for unregistered patietns");
        }
        String query = "SELECT p.patientId, p.patientName, p.age, p.contact, p.email, a.appId, a.status AS status, s.date AS date, s.doctorId, a.userId, GROUP_CONCAT(DISTINCT m.name SEPARATOR ', ') AS medications, GROUP_CONCAT(DISTINCT t.name SEPARATOR ', ') AS tests FROM Appointments a JOIN Schedule s ON a.scheduleId = s.scheduleId LEFT JOIN Medications m ON a.appId = m.appId LEFT JOIN Tests t ON a.appId = t.appId JOIN Patient p ON a.patientId = p.patientId WHERE a.patientId = ? GROUP BY a.appId, s.date, s.doctorId, a.userId, p.patientId;";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            List<AppointmentReport> appointmentReportList = new ArrayList<>();
            PatientReport patientReport = new PatientReport();
            while (rs.next()) {
                AppointmentReport report = new AppointmentReport();
                report.setAppId(rs.getInt("appId"));
                report.setPid(rs.getInt("patientId"));
                report.setPname(rs.getString("patientName"));
                report.setAge(rs.getInt("age"));
                report.setContact(rs.getString("contact"));
                report.setEmail(rs.getString("email"));
                report.setDoctorId(rs.getInt("doctorId"));
                report.setUid(rs.getInt("userId"));
                report.setDate(LocalDate.parse(rs.getString("date")));
                report.setStatus(AppointmentEnums.Status.valueOf(rs.getString("status")));
                report.setMedications(rs.getString("medications"));
                report.setTests(rs.getString("tests"));
                appointmentReportList.add(report);
            }
            patientReport.setReports(appointmentReportList);
            return patientReport;

        }

    }

    public static void main(String[] args) {
        PatientDao patientDao = new PatientDaoImpl();
        try {
//            patientDao.getAllPatients().forEach(System.out::println);
            patientDao.getPatientReport(1).getReports().forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (PatientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
