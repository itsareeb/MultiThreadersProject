package com.hsbc.dao;

import com.hsbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppointmentDaoImpl implements AppointmentDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public AppointmentDaoImpl() {
        conn = DBUtils.getConnection();
    }


    @Override
    public boolean isAppointmentExist(int appointmentID) {
        String sql = "select count(*) from appointments where appointmentID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, appointmentID);
            rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
