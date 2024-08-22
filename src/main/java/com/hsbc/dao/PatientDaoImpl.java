package com.hsbc.dao;

import com.hsbc.Enums.PatientEnums;
import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.models.Patient;
import com.hsbc.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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

    @Override
    public Patient getPatient(String name, String contact) throws PatientNotFoundException, SQLException {
        String query = "SELECT * FROM Patient WHERE name=? AND contact=?";
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

    public static void main(String[] args) {
        PatientDao patientDao = new PatientDaoImpl();
        try {
            System.out.println(patientDao.getPatient("Aquib", "9898989898"));
        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
