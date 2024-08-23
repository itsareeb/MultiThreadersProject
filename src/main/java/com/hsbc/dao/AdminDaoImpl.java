package com.hsbc.dao;

import com.hsbc.Enums.AppointmentEnums;
import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.Enums.PatientEnums;
import com.hsbc.exceptions.*;
import com.hsbc.models.*;
import com.hsbc.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    public Connection conn;

    public AdminDaoImpl() {
        conn = DBUtils.getConnection();
    }


    @Override
    public void importUsers(List<User> users) throws UserAlreadyExistsException{

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();

        }
    }

    @Override
    public List<DoctorSchedule> showDoctorSchedule(int doctorID) throws DoctorNotFoundException {

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

        if(!employeeDao.isEmployee(doctorID, EmployeeEnums.Role.doctor.toString())){
            throw new DoctorNotFoundException("Doctor not found");
        }

        List<DoctorSchedule> schedules = new ArrayList<DoctorSchedule>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from doctor_schedule where doctorID = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, doctorID);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoctorSchedule schedule = new DoctorSchedule();
                schedule.setDoctorId(rs.getInt("doctorID"));
                schedule.setShiftNumber(rs.getInt("shift_number"));
                schedule.setDate(rs.getDate("date"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return schedules;
    }

    @Override
    public void updateDoctorSchedule(DoctorSchedule schedule, int doctorID) throws DoctorNotFoundException, ScheduleNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        if(!employeeDao.isEmployee(doctorID, EmployeeEnums.Role.doctor.toString())){
            throw new DoctorNotFoundException("Doctor not found");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from doctor_schedule where doctorID = ? and date = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, doctorID);
            ps.setDate(2, schedule.getDate());
            rs = ps.executeQuery();
            if (rs.next()) {
                String update_sql = "UPDATE doctor_schedule SET date = ? WHERE doctorID = ? and shift_number = ? ";
                PreparedStatement ps1 = conn.prepareStatement(update_sql);
                ps1.setDate(1, schedule.getDate());
                ps1.setInt(2, doctorID);
                ps1.setInt(3, schedule.getShiftNumber());
                ps1.executeUpdate();
            } else {
                throw new ScheduleNotFoundException("Schedule not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
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
                appointment.setAppointmentId(rs.getInt("appointmentID"));
                appointment.setPatientId(rs.getInt("patientID"));
                appointment.setDate(rs.getDate("date"));
                appointment.setDid(rs.getInt("doctorID"));
                appointment.setUserId(rs.getInt("userID"));
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
        List<Doctor> doctors = new ArrayList<Doctor>();
        PreparedStatement ps = null, ps1 = null;
        ResultSet rs = null, rs1 = null;
        String doctor_sql = "select * from doctor";

        try {
            ps = conn.prepareStatement(doctor_sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setEmpId(rs.getInt(1));
                String emp_sql = "select * from employee where emp_id=?";
                 ps1 = conn.prepareStatement(emp_sql);
                ps1.setInt(1, doctor.getEmpId());
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    doctor.setEmpName(rs1.getString("emp_name"));
                    doctor.setContact(rs1.getString("contact"));
                    doctor.setEmail(rs1.getString("email"));
                    doctor.setRole(EmployeeEnums.Role.valueOf(rs1.getString("role")));
                    doctor.setActive(rs1.getBoolean("isActive"));
                }

                doctor.setSpecialization(rs.getString(3));
                doctor.setQualifications(rs.getString(2));
                doctor.setDept(EmployeeEnums.Department.valueOf(rs.getString(4)));

                doctors.add(doctor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs1.close();
                rs.close();
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return doctors;
    }

    @Override
    public void addDoctor(Doctor doctor) throws DoctorAlreadyExistsException {
        String sql = "INSERT INTO doctor (emp_id, qualifications, specialization, dept) VALUES(?,?,?,?)";
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, doctor.getEmpId());
            ps.setString(2, doctor.getQualifications());
            ps.setString(3, doctor.getSpecialization());
            ps.setString(4, doctor.getDept().toString());
            ps.executeUpdate();
            System.out.println("Doctor Added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeDoctor(int doctorId) throws DoctorNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        if(!employeeDao.isEmployee(doctorId, EmployeeEnums.Role.doctor.toString())){
            throw new DoctorNotFoundException("Doctor not found");
        }

       String sql = "DELETE FROM doctor WHERE emp_id=?";
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, doctorId);
            ps.executeUpdate();
            System.out.println("Doctor Removed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> showAllUsers() throws NoUsersFoundException {
        List<User> users = new ArrayList<User>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from user";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setEmpId(rs.getInt(1));
                user.setEmpName(rs.getString(2));
                user.setRole(EmployeeEnums.Role.valueOf(rs.getString(3)));
                user.setContact(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setActive(rs.getBoolean(6));
                users.add(user);

                //System.out.println(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;

    }

    @Override
    public void removeUser(int userId) throws UserNotFoundException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        if(!employeeDao.isEmployee(userId, EmployeeEnums.Role.user.toString())){
            throw new UserNotFoundException("User not found");
        }
        PreparedStatement ps = null;

        String sql = "UPDATE user SET isActive=false WHERE emp_id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();
            System.out.println("User Removed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        if(employeeDao.isEmployee(user.getEmpId(), EmployeeEnums.Role.user.toString())){
            throw new UserAlreadyExistsException("User already exists");
        }

        // First create entry in employee table
        String emp_sql = "INSERT INTO employee (EmpID, role, name, password,isActive, contact, email) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(emp_sql);
            ps.setInt(1, user.getEmpId());
            ps.setString(2, user.getRole().toString());
            ps.setString(3, user.getEmpName());
            ps.setString(4, user.getPassword());
            ps.setBoolean(5, user.isActive());
            ps.setString(6, user.getContact());
            ps.setString(7, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void importDoctors(List<Doctor> doctors) throws DoctorAlreadyExistsException {
        Iterator<Doctor> iterator = doctors.iterator();
        while (iterator.hasNext()) {
            Doctor doctor = iterator.next();
            addDoctor(doctor);
        }
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
