package com.hsbc.dao;

import com.hsbc.Enums.AppointmentEnums;
import com.hsbc.Enums.PatientEnums;
import com.hsbc.exceptions.AdminAlreadyExistsException;
import com.hsbc.exceptions.AppointmentNotFoundException;
import com.hsbc.exceptions.DoctorNotFoundException;
import com.hsbc.exceptions.UserAlreadyExistsException;
import com.hsbc.models.*;
import com.hsbc.utils.DBUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    Connection conn;

    public AdminDaoImpl() {
        conn = DBUtils.getConnection();
        //System.out.println(conn);
    }


    @Override
    public void importUsersFromXMLFile(String filepath) throws UserAlreadyExistsException, DoctorNotFoundException, AdminAlreadyExistsException {

    }

    @Override
    public void showDoctorSchedule() throws DoctorNotFoundException {

    }

    @Override
    public void updateDoctorSchedule() throws DoctorNotFoundException {

    }

    @Override
    public void cancelAppointments(int appointmentID) throws AppointmentNotFoundException {
        String sql="UPDATE appointments SET status='cancelled' WHERE appointmentID=?";

        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,appointmentID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating appointments schedule", e);
        }




    }

    @Override
    public List<Patient> showAllpatients() {
        List<Patient> patients = new ArrayList<Patient>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from patient";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPid(rs.getInt(1));
                patient.setPname(rs.getString(2));
                patient.setGender(PatientEnums.Gender.valueOf(rs.getString(3)));
                patient.setDob(rs.getInt(4));
                patient.setContact(rs.getString(5));
                patient.setEmail(rs.getString(6));
                patient.setUid(rs.getInt(7));
                patients.add(patient);

                //System.out.println(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public List<Appointment> showAllAppointments() {
        List<Appointment> appointments = new ArrayList<Appointment>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from appointments";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppId(rs.getInt("appointmentID"));
                appointment.setPid(rs.getInt("patientID"));
                appointment.setDate(rs.getDate("date"));
                appointment.setDid(rs.getInt("doctorID"));
                appointment.setUid(rs.getInt("userID"));
                appointment.setShiftNumber(rs.getInt("shift_number"));
                appointment.setSlotNumber(rs.getInt("slot_number"));
                appointment.setStatus(AppointmentEnums.Status.valueOf(rs.getString("status")));
                appointment.setDate(rs.getDate("date"));

                appointments.add(appointment);

                //System.out.println(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointments;
    }

    @Override
    public void showAllDoctors() {

    }


    public static void main(String[] args) {
        AdminDaoImpl adminDao = new AdminDaoImpl();
        /*try {
            adminDao.cancelAppointments(5);
        } catch (AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }*/

        //System.out.println(adminDao.showAllpatients());

        System.out.println(adminDao.showAllAppointments());
    }
}
