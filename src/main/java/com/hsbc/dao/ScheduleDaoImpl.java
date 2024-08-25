package com.hsbc.dao;

import com.hsbc.exceptions.ActionNotAllowedException;
import com.hsbc.exceptions.InvalidScheduleDataException;
import com.hsbc.exceptions.ScheduleNotFoundException;
import com.hsbc.models.DoctorSchedule;
import com.hsbc.models.DoctorSchedules;
import com.hsbc.utils.DBUtils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao {
    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ScheduleDaoImpl() {
        this.conn = DBUtils.getConnection();
    }

//    public static void main(String[] args) {
//        ScheduleDaoImpl scheduleDao = new ScheduleDaoImpl();
////        try {
////            scheduleDao.updateDoctorSchedule(78, false);
////        } catch (SQLException e) {
////            System.out.println(e.getMessage());
////        } catch (ScheduleNotFoundException e) {
////            System.out.println(e.getMessage());
////        }
//        List<DoctorSchedules> docSchedules = new ArrayList<>();
//        docSchedules.add(
//                new DoctorSchedules(16,  LocalDate.of(2024, 8, 25), Arrays.asList(1,3))
//                );
//
//        docSchedules.add(
//                new DoctorSchedules(16, LocalDate.of(2024, 8, 26), Arrays.asList(2,3))
//                );
//        docSchedules.add(
//                new DoctorSchedules(16, LocalDate.of(2024, 8, 27), Arrays.asList(3,4))
//                );
//        try {
//            scheduleDao.addDoctorSchedule(docSchedules);
//        } catch (ActionNotAllowedException e) {
//            System.out.println(e.getMessage());
//        } catch (InvalidScheduleDataException e) {
//            System.out.println(e.getMessage());
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//
////        LocalDate date = LocalDate.of(2024, 8, 24);
////        try {
////            scheduleDao.getDoctorSchedule(101, date);
////        } catch (ScheduleNotFoundException e) {
////            System.out.println(e.getMessage());
////        } catch (SQLException e) {
////            System.out.println(e.getMessage());;
////        }
//    }

    @Override
    public List<DoctorSchedule> getDoctorSchedule(int doctorId, LocalDate date) throws ScheduleNotFoundException, SQLException {
        System.out.println(date.toString());
        String query = "SELECT * FROM Schedule WHERE doctorId = ? AND date = ?";

        List<DoctorSchedule> schedule = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, doctorId);
            ps.setString(2, date.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DoctorSchedule doctorSchedule = new DoctorSchedule();
                    doctorSchedule.setSid(rs.getInt("sid"));
                    doctorSchedule.setDoctorId(rs.getInt("did"));
                    doctorSchedule.setAvailable(rs.getBoolean("isAvailable"));
                    doctorSchedule.setShiftNumber(rs.getInt("shiftno")); // Assuming shiftno is an integer
                    doctorSchedule.setDate(rs.getDate("date").toLocalDate()); // Assuming you want LocalDate here
                    schedule.add(doctorSchedule);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Database access error occurred.", e);
        }

        if (schedule.isEmpty()) {
            throw new ScheduleNotFoundException("No schedule found for the given data");
        }

        schedule.forEach(System.out::println);
        return schedule;
    }


    @Override
    public Boolean addDoctorSchedule(List<DoctorSchedules> dsList) throws ActionNotAllowedException, InvalidScheduleDataException, SQLException {
        if (isValidSchedule(dsList)) {
            String insertIntoSchedule = "Insert into Schedule (doctorId, shiftno, date, isAvailable) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insertIntoSchedule, Statement.RETURN_GENERATED_KEYS);
            conn.setAutoCommit(false);
            for (DoctorSchedules ds : dsList) {
                for (DoctorSchedule schedule: ds.getSchedules()){
                    try {
                        ps.setInt(1, schedule.getDoctorId());
                        ps.setInt(2, schedule.getShiftNumber());
                        ps.setString(3, schedule.getDate().toString());
                        ps.setBoolean(4, schedule.isAvailable());
                        int rows = ps.executeUpdate();

                        if (schedule.isAvailable() && rows>0){
                            ResultSet rs = ps.getGeneratedKeys();
                            if (rs.next()) {
                                int sid = rs.getInt(1);
                                System.out.println(sid);
                                createSlots(sid, schedule.getShiftNumber());

                            }
                        }
                    } catch (SQLException e) {
                        try {
                            conn.rollback();
                            System.out.println(e.getMessage());
                            throw new SQLException("Database access error occurred."+ e.getMessage());
                        } catch (SQLException ex) {
                            throw new SQLException("DB ERROR, " + e);
                        }

                    }
                }
            }
            conn.commit();
            conn.setAutoCommit(true);
        }
        return false;
    }

    private void createSlots(int sid, int shiftno) throws SQLException {
        try(
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Slots (scheduleId, slotno, slotTime) VALUES (?, ?, ?)")
                ){
            LocalTime localTime = LocalTime.of(0, 0);
            switch (shiftno){
                case 1:
                    localTime = LocalTime.of(0, 0);
                    break;
                case 2:
                    localTime = LocalTime.of(6, 0);
                    break;
                case 3:
                    localTime = LocalTime.of(12, 0);
                    break;
                case 4:
                    localTime = LocalTime.of(18, 0);
                    break;
            }

            for (int i = 1; i<=12; i++){
                ps.setInt(1, sid);
                ps.setInt(2, i);
                ps.setString(3, localTime.toString());
                ps.executeUpdate();
                localTime =  localTime.plusMinutes(30);
            }
//            ps.executeBatch();
        } catch (SQLException e) {
            throw new SQLException("db error", e);
        }

    }

    private Boolean isValidSchedule(List<DoctorSchedules> dsList) throws ActionNotAllowedException, InvalidScheduleDataException, SQLException{
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        if (dsList.size()!=3) {
            throw new InvalidScheduleDataException("Schedules must be added for exactly 3 days");
        }
        for(DoctorSchedules ds : dsList) {
            LocalDate scheduleDate = ds.getDate();
            System.out.println(scheduleDate);
            if (scheduleDate.isBefore(tomorrow) || scheduleDate.isAfter(tomorrow.plusDays(2))) {
                throw new InvalidScheduleDataException("Schedules must be added for 3 days starting tomorrow");
            }

        };
        String query = "SELECT * FROM Schedule WHERE doctorId = ? AND date = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, dsList.get(0).getDid());
            ps.setString(2, tomorrow.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    throw new ActionNotAllowedException("Schedules can only be added at intervals of 3 days. Schedule already exists");
                }
                else{
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Database access error occurred.", e);
        }
    }

    private void deleteSlotsAndAppointmentsForSid(int sid) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Slots WHERE scheduleId = ?");
        PreparedStatement deleteAppointmentPs = conn.prepareStatement("UPDATE Appointments SET status='cancelled' WHERE scheduleId=?;");){
            ps.setInt(1, sid);
            deleteAppointmentPs.setInt(1, sid);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Db error", e);
        }
    }

    private DoctorSchedule getDoctorSchedule(int sid) throws SQLException, ScheduleNotFoundException {
        String query = "SELECT * FROM Schedule WHERE scheduleId = ?";
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, sid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    doctorSchedule.setSid(rs.getInt("sid"));
                    doctorSchedule.setDoctorId(rs.getInt("did"));
                    doctorSchedule.setAvailable(rs.getBoolean("isAvailable"));
                    doctorSchedule.setShiftNumber(rs.getInt("shiftno"));
                    doctorSchedule.setDate(rs.getDate("date").toLocalDate());
                }
                else{
                    throw new ScheduleNotFoundException("Invalid sid");
                }
            }
        }
        return doctorSchedule;
    }

    @Override
    public void updateDoctorSchedule(int sid, boolean isAvailable) throws SQLException, ScheduleNotFoundException {
        String query = "UPDATE Schedule SET isAvailable = ? WHERE scheduleId = ?";
        try(
                PreparedStatement ps = conn.prepareStatement(query);
                ){
            conn.setAutoCommit(false);
            DoctorSchedule schedule = getDoctorSchedule(sid);
            System.out.println(schedule);
            ps.setBoolean(1, isAvailable);
            ps.setInt(2, sid);
            if (schedule.isAvailable() == isAvailable) {
                System.out.println("No change required");
                return;
            } else if (isAvailable) {
                System.out.println("Schedule seting to available");
                ps.executeUpdate();
                createSlots(sid, schedule.getShiftNumber());
                conn.commit();
                System.out.println("success");
            }
            else {
                System.out.println("Schedule set to not available");
                ps.executeUpdate();
                deleteSlotsAndAppointmentsForSid(sid);
                conn.commit();
                System.out.println("success");
            }

        } catch (SQLException e) {
            throw new SQLException("DB error", e);
        }
    }
}
