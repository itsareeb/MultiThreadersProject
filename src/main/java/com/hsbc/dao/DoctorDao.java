package com.hsbc.dao;


import com.hsbc.models.Appointment;
import com.hsbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import static com.hsbc.utils.DBUtils.conn;

public class DoctorDao {

    Connection conn;

    public DoctorDao() {
        conn = DBUtils.getConnection();
        //System.out.println(conn);
    }

    public List<Appointment> viewAppointements(int id){
        List<Appointment> appointments = new ArrayList<Appointment>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from appointments where doctorID = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppId(rs.getInt("appointmentID"));
                appointment.setDate(rs.getDate("date"));
                appointment.setDid(rs.getInt("doctorID"));
                appointment.setUid(rs.getInt("userID"));
                appointments.add(appointment);

                //System.out.println(appointment);
        }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return appointments;
    }



    public static void main(String[] args) {
        DoctorDao dao = new DoctorDao();
        System.out.println(dao.viewAppointements(305));
    }
}
