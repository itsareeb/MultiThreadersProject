package com.hsbc.dao;

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
    public Boolean isRegisteredPatient(int id){
        String query = "SELECT * FROM Patient WHERE pid=?";

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
            throw new RuntimeException(e);
        }
        finally {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
