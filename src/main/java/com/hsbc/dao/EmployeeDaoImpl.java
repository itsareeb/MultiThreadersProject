package com.hsbc.dao;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.exceptions.*;
import com.hsbc.models.*;
import com.hsbc.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public EmployeeDaoImpl() {
        this.conn = DBUtils.getConnection();
    }

    @Override
    public Employee employeeLogin(String email, String password) throws EmployeeNotFoundException, SQLException {
        String query = "SELECT * FROM Employee WHERE email = ? AND password = ? AND isActive=TRUE";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmail(rs.getString("email"));
                employee.setEmpId(rs.getInt("empID"));
                employee.setEmpName(rs.getString("name"));
                employee.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setContact(rs.getString("contact"));
                System.out.println(employee);
                return employee;
            } else {
                throw new EmployeeNotFoundException("Invalid credentials");
            }
        }
    }

    @Override
    public Boolean isValidEmployee(int empId, EmployeeEnums.Role role)  {
        String query = "SELECT * FROM Employee WHERE empId = ? AND role = ? AND isActive=TRUE";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, empId);
            ps.setString(2, String.valueOf(role));
            rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmail(rs.getString("email"));
                employee.setEmpId(rs.getInt("empID"));
                employee.setEmpName(rs.getString("empName"));
                employee.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setContact(rs.getString("contact"));
//                System.out.println(employee);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean isValidEmployee(String email, EmployeeEnums.Role role) {
        String query = "SELECT * FROM Employee WHERE email = ? AND role = ? AND isActive=TRUE";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, String.valueOf(role));
            rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmail(rs.getString("email"));
                employee.setEmpId(rs.getInt("empID"));
                employee.setEmpName(rs.getString("empName"));
                employee.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setContact(rs.getString("contact"));
                System.out.println(employee);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee addDoctor(Doctor doctor) throws SQLException, DoctorAlreadyExistsException {

        if(isValidEmployee(doctor.getEmail(), EmployeeEnums.Role.doctor)){
            throw new DoctorAlreadyExistsException("Doctor already exists");
        }

        String query = "INSERT INTO Employee (empName, role, password, contact, email) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            ps.setString(1, doctor.getEmpName());
            ps.setString(2, doctor.getRole().toString());
            ps.setString(3, doctor.getPassword());
            ps.setString(4, doctor.getContact());
            ps.setString(5, doctor.getEmail());
            int rows = ps.executeUpdate();
            System.out.println(rows);
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    doctor.setEmpId(rs.getInt(1));
                    String doctorInsertQuery = "INSERT INTO Doctor (doctorId, qualification, specialization, department) VALUES (?,?,?,?)";
                    PreparedStatement docPs = conn.prepareStatement(doctorInsertQuery);
                    docPs.setInt(1, doctor.getEmpId());
                    docPs.setString(2, doctor.getQualifications());
                    docPs.setString(3, doctor.getSpecialization());
                    docPs.setString(4, String.valueOf(doctor.getDept()));
                    docPs.executeUpdate();
                    System.out.println(doctor);
                }
            } else {
                throw new SQLException("Some error occured while creating employee");
            }
        } catch (SQLException e) {
            conn.rollback();
            System.out.println(e.getMessage());
            throw new SQLException("DB error: ", e.getMessage());
        } finally {
            conn.commit();
            conn.setAutoCommit(true);
        }
        return doctor;
    }

    @Override
    public Employee addUser(User user) throws SQLException, UserAlreadyExistsException {

        if(isValidEmployee(user.getEmail(), EmployeeEnums.Role.user)){
            throw new UserAlreadyExistsException("User already exists");
        }

        String query = "INSERT INTO Employee (empName, role, password, contact, email) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            ps.setString(1, user.getEmpName());
            ps.setString(2, user.getRole().toString());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getContact());
            ps.setString(5, user.getEmail());
            int rows = ps.executeUpdate();
            System.out.println(rows);
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    user.setEmpId(rs.getInt(1));
                    String doctorInsertQuery = "INSERT INTO User (userId, department, shift) VALUES (?,?,?)";
                    PreparedStatement docPs = conn.prepareStatement(doctorInsertQuery);
                    docPs.setInt(1, user.getEmpId());
                    docPs.setString(2, String.valueOf(user.getDept()));
                    docPs.setString(3, String.valueOf(user.getShift()));
                    docPs.executeUpdate();
                    System.out.println(user);
                }
            } else {
                throw new SQLException("Some error occured while creating employee");
            }
        } catch (SQLException e) {
            conn.rollback();
            System.out.println(e.getMessage());
            throw new SQLException("DB error: ", e.getMessage());
        } finally {
            conn.commit();
            conn.setAutoCommit(true);
        }
        return user;
    }

    @Override
    public Employee updateDoctor(Doctor doctor) throws SQLException, EmployeeNotFoundException {

        if(!isValidEmployee(doctor.getEmail(), EmployeeEnums.Role.doctor)){
            throw new EmployeeNotFoundException("No doctor with this email");
        }

        Employee emp = getEmployee(doctor.getEmail());
        String updateEmpQuery = "UPDATE Employee SET empName = ?, password = ?, isActive = ?, contact = ? WHERE email = ?";
        String updateDocQuery = "UPDATE Doctor SET qualification = ?, specialization = ?, department = ? WHERE doctorId = ?";
        conn.setAutoCommit(false);
        try (
                PreparedStatement empPs = conn.prepareStatement(updateEmpQuery, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement docPs = conn.prepareStatement(updateDocQuery)
        ) {
            empPs.setString(1, doctor.getEmpName());
            empPs.setString(2, doctor.getPassword());
            empPs.setBoolean(3, doctor.isActive());
            empPs.setString(4, doctor.getContact());
            empPs.setString(5, doctor.getEmail());
            int row = empPs.executeUpdate();
            if (row > 0) {
                doctor.setEmpId(emp.getEmpId());
                docPs.setString(1, doctor.getQualifications());
                docPs.setString(2, doctor.getSpecialization());
                docPs.setString(3, String.valueOf(doctor.getDept()));
                docPs.setInt(4, doctor.getEmpId());
                docPs.executeUpdate();
            }
        }
        conn.commit();
        conn.setAutoCommit(true);
        return doctor;
    }

    @Override
    public Employee updateUser(User user) throws SQLException, EmployeeNotFoundException {

        if(!isValidEmployee(user.getEmail(), EmployeeEnums.Role.user)){
            throw new EmployeeNotFoundException("No user with this email");
        }

        Employee emp = getEmployee(user.getEmail());
        String updateEmpQuery = "UPDATE Employee SET empName = ?, password = ?, isActive = ?, contact = ? WHERE email = ?";
        String updateDocQuery = "UPDATE User SET department = ?, shift = ? WHERE userId = ?";
        conn.setAutoCommit(false);
        System.out.println("updating");
        try(
                PreparedStatement empPs = conn.prepareStatement(updateEmpQuery, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement userPs = conn.prepareStatement(updateDocQuery)
        ) {
            empPs.setString(1, user.getEmpName());
            empPs.setString(2, user.getPassword());
            empPs.setBoolean(3, user.isActive());
            empPs.setString(4, user.getContact());
            empPs.setString(5, user.getEmail());
            System.out.println(user);
            int row = empPs.executeUpdate();
            System.out.println(row);
            if (row > 0) {
                user.setEmpId(emp.getEmpId());
                userPs.setString(1, user.getDept().toString());
                userPs.setString(2, user.getShift().toString());
                userPs.setInt(3, user.getEmpId());
                userPs.executeUpdate();
                conn.commit();
            }
        }

        conn.setAutoCommit(true);
        return user;
    }

    @Override
    public boolean deleteEmployee(int empId) throws SQLException, EmployeeNotFoundException {

        if(!isValidEmployee(empId, EmployeeEnums.Role.doctor) && !isValidEmployee(empId, EmployeeEnums.Role.user)){
            throw new EmployeeNotFoundException("No employee with this id");
        }


        String query = "UPDATE Employee SET isActive=FALSE WHERE empId=?";
        try(
                PreparedStatement ps = conn.prepareStatement(query)
                ){
            ps.setInt(1, empId);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(String email) throws SQLException, EmployeeNotFoundException {

        if(!isValidEmployee(email, EmployeeEnums.Role.doctor) && !isValidEmployee(email, EmployeeEnums.Role.user)){
            throw new EmployeeNotFoundException("No employee with this email");
        }

        String query = "UPDATE Employee SET isActive=FALSE WHERE email=?";
        try(
                PreparedStatement ps = conn.prepareStatement(query)
        ){
            ps.setString(1, email);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addDoctors(List<Doctor> doctors) throws SQLException {

        try {
            for (Doctor doctor : doctors) {
                addDoctor(doctor);
            }
        } catch(DoctorAlreadyExistsException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addUsers(List<User> users) throws SQLException {
        try {
            for (User user : users) {
                addUser(user);
            }
        } catch(UserAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Employee getEmployee(int empId) throws SQLException, EmployeeNotFoundException{
        String query = "SELECT * FROM Employee WHERE empId = ?";
        try(
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpId(rs.getInt("empId"));
                employee.setEmpName(rs.getString("empName"));
                employee.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setContact(rs.getString("contact"));
                employee.setEmail(rs.getString("email"));
                return employee;
            }
            else{
                throw new EmployeeNotFoundException("No employee with this id");
            }

        }
    }


    @Override
    public Employee getEmployee(String email) throws SQLException, EmployeeNotFoundException {
        String query = "SELECT * FROM Employee WHERE email = ?";
        try(
                PreparedStatement ps = conn.prepareStatement(query);

                ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpId(rs.getInt("empId"));
                employee.setEmpName(rs.getString("empName"));
                employee.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setContact(rs.getString("contact"));
                employee.setEmail(rs.getString("email"));
                return employee;
            }
            else{
                throw new EmployeeNotFoundException("No employee with this email");
            }

        }
    }



    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        String query = "SELECT * FROM Employee";
        List<Employee> employees = new ArrayList<>();
        try(
                PreparedStatement ps = conn.prepareStatement(query)
                ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpId(rs.getInt("empId"));
                employee.setEmpName(rs.getString("empName"));
                employee.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setContact(rs.getString("contact"));
                employee.setEmail(rs.getString("email"));
                employees.add(employee);
            }
            return employees;

        }
    }
    @Override
    public List<Doctor> getAllDoctors() throws SQLException, NoDoctorsFoundException {
        String query = "SELECT * FROM Employee WHERE role = 'doctor'";
        List<Doctor> doctors = new ArrayList<>();
        try(
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setEmpId(rs.getInt("empId"));
                doctor.setEmpName(rs.getString("empName"));
                doctor.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                doctor.setActive(rs.getBoolean("isActive"));
                doctor.setContact(rs.getString("contact"));
                doctor.setEmail(rs.getString("email"));
                doctors.add(doctor);
            }
            if(doctors.isEmpty()){
                throw new NoDoctorsFoundException("No doctors found");
            }
            return doctors;

        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM Employee WHERE role = 'user'";
        List<User> users = new ArrayList<>();
        try(
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setEmpId(rs.getInt("empId"));
                user.setEmpName(rs.getString("empName"));
                user.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                user.setActive(rs.getBoolean("isActive"));
                user.setContact(rs.getString("contact"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
            return users;

        }
    }

    public DoctorReport getDoctorReport(int doctorId) throws SQLException, EmployeeNotFoundException, NoRecordFoundException {
        if(!isValidEmployee(doctorId, EmployeeEnums.Role.doctor)){
            throw new EmployeeNotFoundException("No doctor with this doctorId");
        }
        String query = "SELECT e.empId, e.empName, e.role, e.isActive, e.contact, e.email, d.qualification, d.specialization, d.department, COUNT(a.appId) AS totalAppointments, COUNT(CASE WHEN a.status = 'cancelled' THEN 1 END) AS cancelledAppointments, COUNT(CASE WHEN a.status = 'completed' THEN 1 END) AS completedAppointments, COUNT(CASE WHEN a.status = 'pending' THEN 1 END) AS pendingAppointments FROM Doctor d JOIN Employee e ON d.doctorId = e.empId LEFT JOIN Schedule s ON d.doctorId = s.doctorId LEFT JOIN Appointments a ON s.scheduleId = a.scheduleId WHERE d.doctorId = ? GROUP BY e.empId;";
        try(PreparedStatement ps =conn.prepareStatement(query)){
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DoctorReport report = new DoctorReport();
                report.setEmpId(rs.getInt("empId"));
                report.setEmpName(rs.getString("empName"));
                report.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                report.setActive(rs.getBoolean("isActive"));
                report.setContact(rs.getString("contact"));
                report.setEmail(rs.getString("email"));
                report.setQualifications(rs.getString("qualification"));
                report.setSpecialization(rs.getString("specialization"));
                report.setDept(EmployeeEnums.Department.valueOf(rs.getString("department")));
                report.setNumTotalAppointments(rs.getInt("totalAppointments"));
                report.setNumCancelledAppointments(rs.getInt("cancelledAppointments"));
                report.setNumCompletedAppointments(rs.getInt("completedAppointments"));
                report.setNumPendingAppointments(rs.getInt("pendingAppointments"));
                return report;
            }
            throw new NoRecordFoundException("No record of this employee is available");
        }
    }

    @Override
    public UserReport getUserReport(int userId) throws SQLException, EmployeeNotFoundException, NoRecordFoundException {
        if(!isValidEmployee(userId, EmployeeEnums.Role.user)){
            throw new EmployeeNotFoundException("No doctor with this doctorId");
        }
        String query = "SELECT e.empId, e.empName, e.role, e.contact, e.email, e.isActive, u.department, u.shift, COUNT(DISTINCT p.patientId) AS numPatients, COUNT(a.appId) AS numAppointments FROM User u JOIN Employee e ON u.userId = e.empId LEFT JOIN Patient p ON u.userId = p.userId LEFT JOIN Appointments a ON u.userId = a.userId WHERE u.userId = ? GROUP BY e.empId;";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserReport report = new UserReport();
                report.setEmpId(rs.getInt("empId"));
                report.setEmpName(rs.getString("empName"));
                report.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                report.setActive(rs.getBoolean("isActive"));
                report.setContact(rs.getString("contact"));
                report.setEmail(rs.getString("email"));
                report.setDept(EmployeeEnums.Department.valueOf(rs.getString("department")));
                report.setShift(EmployeeEnums.Shift.valueOf(rs.getString("shift")));
                report.setNumPatients(rs.getInt("numPatients"));
                report.setNumAppointments(rs.getInt("numAppointments"));
                return report;
            }
            throw new NoRecordFoundException("No record of this employee is available");
        }
    }

    public static void main(String[] args) {
        EmployeeDaoImpl dao = new EmployeeDaoImpl();
        Doctor doctor = new Doctor(
                EmployeeEnums.Role.doctor,
                "Dr Aimon",
                "Password123",
                true,
                "9098909890",
                "mdareeb176@gmil.com",
                "MBBS, MS",
                "Neurosurgeon",
                EmployeeEnums.Department.emergency
        );
        try {
            System.out.println(
            dao.getUserReport(21));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NoRecordFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
