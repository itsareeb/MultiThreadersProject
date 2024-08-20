package com.hsbc.dao;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.exceptions.EmployeeNotFoundException;
import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.models.Employee;
import com.hsbc.models.Patient;
import com.hsbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthDaoImpl implements AuthDao {
    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public AuthDaoImpl() {
        this.conn = DBUtils.getConnection();
    }

    @Override
    public Employee employeeLogin(String email, String password) throws EmployeeNotFoundException {
        String query = "SELECT * FROM Employee WHERE email = ? AND password = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmail(rs.getString("email"));
                employee.setEmpId(rs.getInt("emp_id"));
                employee.setEmpName(rs.getString("emp_name"));
                employee.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setContact(rs.getString("contact"));
                return employee;
            }
            else{
                throw new EmployeeNotFoundException("Invalid credentials");
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

    @Override
    public Boolean isEmployee(int id, String role){
        String query = "SELECT * FROM Employee WHERE emp_id = ? AND role = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, role);
            rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmail(rs.getString("email"));
                employee.setEmpId(rs.getInt("emp_id"));
                employee.setEmpName(rs.getString("emp_name"));
                employee.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setContact(rs.getString("contact"));
                System.out.println(employee);
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

    public static void main(String[] args) {
        AuthDaoImpl authDao = new AuthDaoImpl();

        try {
            System.out.println(authDao.employeeLogin("mdareeb176@gmail.com", "Test01@123"));
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e);
        }


        System.out.println(authDao.isEmployee(1,"user"));

        System.out.println(authDao.isRegisteredPatient(1));

    }
}
