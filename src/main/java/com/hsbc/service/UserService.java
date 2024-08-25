package com.hsbc.service;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.dao.EmployeeDao;
import com.hsbc.dao.EmployeeDaoImpl;
import com.hsbc.exceptions.NoUsersFoundException;
import com.hsbc.exceptions.UserAlreadyExistsException;
import com.hsbc.exceptions.UserNotFoundException;
import com.hsbc.models.User;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public void addUser(String userName, String password, String email, String contact, EmployeeEnums.Shift shift, EmployeeEnums.Department dept) {
        User user = new User(EmployeeEnums.Role.user, userName, password, true, contact, email, dept, shift);
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.addUser(user);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllUsers() {
        EmployeeDao dao = new EmployeeDaoImpl();
        List<User> users =  null;

        try {
            users = dao.getAllUsers();
            for(User user: users) {
                System.out.println(user);
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeUser(int userId) {
        EmployeeDao dao = new EmployeeDaoImpl();
        try {
            dao.deleteEmployee(userId);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void importUsers(String filepath){
        // Import users from a json file and then add them to the database

        File file = new File(filepath);
        EmployeeDao dao = new EmployeeDaoImpl();

        // Parse this file, validate file json format or xml format, and then store it in a List<User> then call Dao method

        // dao.addUsers();

    }
}
