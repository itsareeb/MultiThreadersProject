package com.hsbc.dao;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.exceptions.EmployeeNotFoundException;
import com.hsbc.models.Employee;
import com.hsbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public EmployeeDaoImpl() {
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
                employee.setEmpId(rs.getInt("empID"));
                employee.setEmpName(rs.getString("name"));
                employee.setRole(EmployeeEnums.Role.valueOf(rs.getString("role")));
                employee.setActive(rs.getBoolean("isActive"));
                employee.setContact(rs.getString("contact"));
                System.out.println(employee);
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
        String query = "SELECT * FROM Employee WHERE empID = ? AND role = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, role);
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
        EmployeeDaoImpl dao = new EmployeeDaoImpl();
        try {
            dao.employeeLogin("user1@gmail.com","p1234");
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
