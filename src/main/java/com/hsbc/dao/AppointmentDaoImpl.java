package com.hsbc.dao;

import com.hsbc.Enums.AppointmentEnums;
import com.hsbc.exceptions.AppointmentNotFoundException;
import com.hsbc.exceptions.SlotAlreadyBookedException;
import com.hsbc.exceptions.SlotNotAvailableException;
import com.hsbc.models.Appointment;
import com.hsbc.models.Medication;
import com.hsbc.models.ShiftSlot;
import com.hsbc.models.Test;
import com.hsbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public AppointmentDaoImpl() {
        conn = DBUtils.getConnection();
    }

    @Override
    public boolean isAppointmentExist(int appointmentId) {
        String sql = "select * from Appointments where appId = ?";
        try(
               PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setInt(1, appointmentId);
            rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Appointment getAppointment(int appId) throws SQLException, AppointmentNotFoundException {
        String sql = "SELECT Appointments.appId, Schedule.doctorId, Appointments.userId, Appointments.patientId, Appointments.scheduleId, Appointments.slotno, Appointments.status, Appointments.createdAt, Appointments.updatedAt, Patient.patientName, Schedule.date, Slots.slotTime FROM Appointments LEFT JOIN Patient ON Appointments.patientId = Patient.patientId LEFT JOIN Schedule ON Appointments.scheduleId = Schedule.scheduleId LEFT JOIN Slots ON Appointments.scheduleId = Slots.scheduleId AND Appointments.slotno = Slots.slotno WHERE Appointments.appId = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, appId);
            rs = ps.executeQuery();
            if(rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appId"));
                appointment.setScheduleId(rs.getInt("scheduleId"));
                appointment.setSlotNumber(rs.getInt("slotno"));
                appointment.setPatientId(rs.getInt("patientId"));
                appointment.setUserId(rs.getInt("userId"));
                appointment.setStatus(AppointmentEnums.Status.valueOf(rs.getString("status")));
                appointment.setDate(LocalDate.parse(rs.getString("date")));
                appointment.setPatientName(rs.getString("patientName"));
                appointment.setSlotTime(rs.getString("slotTime"));
                appointment.setDoctorId(rs.getInt("doctorId"));

                return appointment;
            }else{
                throw new SQLException("db insert query error");
            }
        }
    }

    @Override
    public synchronized void bookAppointment(Appointment appointment) throws SQLException, SlotAlreadyBookedException {
        if (!isSlotAvailable(appointment.getScheduleId(), appointment.getSlotNumber())) {
            throw new SlotAlreadyBookedException("appointment cant be booked since this slot is already booked.");
        }
        conn.setAutoCommit(false);
        String sql = "INSERT INTO Appointments (userId, patientId, scheduleId, slotno) VALUES (?,?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, appointment.getUserId());
            ps.setInt(2, appointment.getPatientId());
            ps.setInt(3, appointment.getScheduleId());
            ps.setInt(4, appointment.getSlotNumber());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                bookSlot(appointment.getScheduleId(), appointment.getSlotNumber());
                conn.commit();
            }
        }catch (SQLException e) {
            conn.rollback();
            throw new SQLException("DB error," + e.getMessage());
        }
        conn.setAutoCommit(true);
    }

    private void bookSlot(int scheduleId, int slotno) throws SQLException
    {
        String sql = "UPDATE Slots SET isBooked = TRUE WHERE scheduleId = ? AND slotno = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, scheduleId);
            ps.setInt(2, slotno);
            int rows = ps.executeUpdate();
        }
    }

    @Override
    public void updateAppointment(int appId, AppointmentEnums.Status status) throws SQLException, SlotNotAvailableException {
        String sql = "update Appointments set status = ? where appId = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, status.toString());
            ps.setInt(2, appId);
            ps.executeUpdate();
        }
    }

    public boolean isSlotAvailable(int scheduleId, int slotno) throws SQLException {
        String sql = "select * from Slots where scheduleId = ? and slotno = ? and isBooked = false";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, scheduleId);
            ps.setInt(2, slotno);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    @Override
    public void cancelAppointment(int appointmentId) throws SQLException {
        String sql = "update Appointments set status = 'canceled' where appId = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, appointmentId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Appointment> getAllAppointments(LocalDate date) throws SQLException {
        String sql = "SELECT Appointments.appId, Schedule.doctorId, Appointments.userId, Appointments.patientId, Appointments.scheduleId, Appointments.slotno, Appointments.status, Appointments.createdAt, Appointments.updatedAt, Patient.patientName, Schedule.date, Slots.slotTime FROM Appointments LEFT JOIN Patient ON Appointments.patientId = Patient.patientId LEFT JOIN Schedule ON Appointments.scheduleId = Schedule.scheduleId LEFT JOIN Slots ON Appointments.scheduleId = Slots.scheduleId AND Appointments.slotno = Slots.slotno WHERE Schedule.date = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, date.toString());
            rs = ps.executeQuery();
            List<Appointment> appointments = new ArrayList<>();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appId"));
                appointment.setScheduleId(rs.getInt("scheduleId"));
                appointment.setSlotNumber(rs.getInt("slotno"));
                appointment.setPatientId(rs.getInt("patientId"));
                appointment.setUserId(rs.getInt("userId"));
                appointment.setStatus(AppointmentEnums.Status.valueOf(rs.getString("status")));
                appointment.setDate(LocalDate.parse(rs.getString("date")));
                appointment.setPatientName(rs.getString("patientName"));
                appointment.setSlotTime(rs.getString("slotTime"));
                appointment.setDoctorId(rs.getInt("doctorId"));
                appointments.add(appointment);
            }
            return appointments;
        }
    }

    @Override
    public List<Appointment> getAllAppointments(int doctorId, LocalDate date) throws SQLException {
        String sql = "SELECT Appointments.appId, Schedule.doctorId, Appointments.userId, Appointments.patientId, Appointments.scheduleId, Appointments.slotno, Appointments.status, Appointments.createdAt, Appointments.updatedAt, Patient.patientName, Schedule.date, Slots.slotTime FROM Appointments LEFT JOIN Patient ON Appointments.patientId = Patient.patientId LEFT JOIN Schedule ON Appointments.scheduleId = Schedule.scheduleId LEFT JOIN Slots ON Appointments.scheduleId = Slots.scheduleId AND Appointments.slotno = Slots.slotno WHERE Schedule.date = ? AND Schedule.doctorId = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, date.toString());
            ps.setInt(2, doctorId);
            rs = ps.executeQuery();
            List<Appointment> appointments = new ArrayList<>();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appId"));
                appointment.setScheduleId(rs.getInt("scheduleId"));
                appointment.setSlotNumber(rs.getInt("slotno"));
                appointment.setPatientId(rs.getInt("patientId"));
                appointment.setUserId(rs.getInt("userId"));
                appointment.setStatus(AppointmentEnums.Status.valueOf(rs.getString("status")));
                appointment.setDate(LocalDate.parse(rs.getString("date")));
                appointment.setPatientName(rs.getString("patientName"));
                appointment.setSlotTime(rs.getString("slotTime"));
                appointment.setDoctorId(rs.getInt("doctorId"));
                appointments.add(appointment);
            }
            return appointments;
        }
    }

    @Override
    public List<Appointment> getAllAppointments() throws SQLException {
        String sql = "SELECT Appointments.appId, Schedule.doctorId Appointments.userId, Appointments.patientId, Appointments.scheduleId, Appointments.slotno, Appointments.status, Patient.patientName, Schedule.date, Slots.slotTime FROM Appointments LEFT JOIN Patient ON Appointments.patientId = Patient.patientId LEFT JOIN Schedule ON Appointments.scheduleId = Schedule.scheduleId LEFT JOIN Slots ON Appointments.scheduleId = Slots.scheduleId AND Appointments.slotno = Slots.slotno;";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            rs = ps.executeQuery();
            List<Appointment> appointments = new ArrayList<>();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appId"));
                appointment.setScheduleId(rs.getInt("scheduleId"));
                appointment.setSlotNumber(rs.getInt("slotno"));
                appointment.setPatientId(rs.getInt("patientId"));
                appointment.setUserId(rs.getInt("userId"));
                appointment.setStatus(AppointmentEnums.Status.valueOf(rs.getString("status")));
                appointment.setDate(LocalDate.parse(rs.getString("date")));
                appointment.setPatientName(rs.getString("patientName"));
                appointment.setSlotTime(rs.getString("slotTime"));
                appointment.setDoctorId(rs.getInt("doctorId"));
                appointments.add(appointment);
            }
            return appointments;
        }
    }



    @Override
    public void suggestMedicines(Medication medication) throws SQLException, AppointmentNotFoundException {
        if (!isAppointmentExist(medication.getAppId())){
            throw new AppointmentNotFoundException("Appointment not found");
        }
        String sql = "INSERT INTO Medications (appId, name, dosage, instructions) VALUES (?,?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, medication.getAppId());
            ps.setString(2, medication.getName());
            ps.setString(3, medication.getDosage());
            ps.setString(4, medication.getInstructions());
            ps.executeUpdate();
        }
    }

    @Override
    public void suggestTests(Test test) throws SQLException, AppointmentNotFoundException {
        if (!isAppointmentExist(test.getAppId())){
            throw new AppointmentNotFoundException("Appointment not found");
        }
        String sql = "INSERT INTO Tests (appId, testName) VALUES (?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, test.getAppId());
            ps.setString(2, test.getName());
            ps.executeUpdate();
        }

    }

    @Override
    public List<ShiftSlot> getAvailableSlots(int doctorId, LocalDate date) throws SQLException {
        String query = "SELECT Slots.* FROM Slots LEFT JOIN Schedule ON Slots.scheduleId=Schedule.scheduleId WHERE Schedule.doctorId=? AND Schedule.date=? AND Slots.isBooked=FALSE";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, doctorId);
            ps.setString(2, date.toString());
            rs = ps.executeQuery();
            List<ShiftSlot> slots = new ArrayList<>();
            while(rs.next()) {
                ShiftSlot slot = new ShiftSlot();
                slot.setScheduleId(rs.getInt("scheduleId"));
                slot.setSlotno(rs.getInt("slotno"));
                slot.setSlotTime(rs.getString("slotTime"));
                slot.setBooked(rs.getBoolean("isBooked"));
                slots.add(slot);
            }
            return slots;
        }
    }

    @Override
    public List<Medication> getMedications(int appId) throws SQLException, AppointmentNotFoundException {
        if (!isAppointmentExist(appId)){
            throw new AppointmentNotFoundException("No appointment with this id");
        }
        String query = "SELECT * FROM Medications WHERE appId = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, appId);
            ResultSet rs = ps.executeQuery();
            List<Medication> medications = new ArrayList<>();
            while (rs.next()){
                Medication medication = new Medication();
                medication.setAppId(appId);
                medication.setMid(rs.getInt("medicationId"));
                medication.setName(rs.getString("name"));
                medication.setDosage(rs.getString("dosage"));
                medication.setInstructions(rs.getString("instructions"));
                medications.add(medication);
            }
            return medications;
        }
    }

    @Override
    public List<Test> getTests(int appId) throws SQLException, AppointmentNotFoundException {
        if (!isAppointmentExist(appId)){
            throw new AppointmentNotFoundException("No appointment with this id");
        }
        String query = "SELECT * FROM Tests WHERE appId = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, appId);
            ResultSet rs = ps.executeQuery();
            List<Test> tests = new ArrayList<>();
            while (rs.next()){
                Test test = new Test();
                test.setTid(rs.getInt("testId"));
                test.setAppId(appId);
                test.setName(rs.getString("name"));
                tests.add(test);
            }
            return tests;
        }
    }

    public static void main(String[] args) {
        Appointment appt = new Appointment(21, 1, 1, 2);

        AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
        try {
            appointmentDao.getTests(2).forEach(System.out::println);
//            System.out.println(appointmentDao.getAppointment(2));
//            appointmentDao.suggestTests(new Test(1, "Bluribin"));
//            appointmentDao.suggestMedicines(new Medication(1, "Azithromycin 500", "2x5", "After meal"));

//            appointmentDao.getAllAppointments(16, LocalDate.of(2024, 8, 25)).forEach(System.out::println);
//            System.out.println(
//                    appointmentDao.isSlotAvailable(1,1)
//            );
//            appointmentDao.bookAppointment(appt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }
//        catch (SlotAlreadyBookedException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            appointmentDao.getAvailableSlots(16, LocalDate.of(2024, 8, 25)).forEach(System.out::println);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
