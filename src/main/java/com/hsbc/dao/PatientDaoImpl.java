package com.hsbc.dao;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.Enums.PatientEnums;
import com.hsbc.exceptions.PatientAlreadyExistsException;
import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.exceptions.UserNotFoundException;
import com.hsbc.models.Patient;
import com.hsbc.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                patient.setPname(rs.getString("name"));
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

    public static void main(String[] args) {
        PatientDao patientDao = new PatientDaoImpl();
        try {
            patientDao.getAllPatients().forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }


}
