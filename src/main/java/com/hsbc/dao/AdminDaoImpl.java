package com.hsbc.dao;

import com.hsbc.Enums.AppointmentEnums;
import com.hsbc.Enums.PatientEnums;
import com.hsbc.exceptions.*;
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
    public void importUsers(List<User> users) throws UserAlreadyExistsException{

    }

    @Override
    public List<DoctorSchedule> showDoctorSchedule(int doctorID) throws DoctorNotFoundException {

    }

    @Override
    public void updateDoctorSchedule(DoctorSchedule schedule, int doctorID) throws DoctorNotFoundException, ScheduleNotFoundException {

    }

    @Override
    public void cancelAppointment(int appointmentID) throws AppointmentNotFoundException {
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
    public List<Patient> showAllPatients() throws NoPatientsFoundException {
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
                patient.setAge(rs.getInt(4));
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
    public List<Appointment> showAllAppointments() throws NoAppointmentsFoundException {
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
    public List<Doctor> showAllDoctors() throws NoDoctorsFoundException{

    }

    @Override
    public void addDoctor(Doctor doctor) throws DoctorAlreadyExistsException {

    }

    @Override
    public void removeDoctor(int doctorID) throws DoctorNotFoundException {

    }

    @Override
    public List<User> showAllUsers() throws NoUsersFoundException {
        return List.of();
    }

    @Override
    public void removeUser(int userID) throws UserNotFoundException {

    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException {

    }

    @Override
    public void importDoctors(List<Doctor> doctors) throws DoctorAlreadyExistsException {

    }


    public static void main(String[] args) {
        AdminDaoImpl adminDao = new AdminDaoImpl();
        /*try {
            adminDao.cancelAppointment(5);
        } catch (AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }*/

        //System.out.println(adminDao.showAllpatients());

//        System.out.println(adminDao.showAllAppointments());
    }
}
