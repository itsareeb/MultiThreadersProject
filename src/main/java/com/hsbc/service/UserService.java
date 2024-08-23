package com.hsbc.service;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.dao.AdminDao;
import com.hsbc.dao.AdminDaoImpl;
import com.hsbc.exceptions.NoUsersFoundException;
import com.hsbc.exceptions.UserAlreadyExistsException;
import com.hsbc.exceptions.UserNotFoundException;
import com.hsbc.models.User;

import java.io.File;
import java.util.List;

public class UserService {

    public void addUser(String userName, String password, String email, String contact, EmployeeEnums.Shift shift, EmployeeEnums.Department dept) {
        User user = new User(EmployeeEnums.Role.user, userName, password, true, contact, email, dept, shift);
        AdminDao dao = new AdminDaoImpl();
        try {
            dao.addUser(user);
        } catch(UserAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllUsers() {
        AdminDao dao = new AdminDaoImpl();
        List<User> users =  null;

        try {
            users = dao.showAllUsers();
            for(User user: users) {
                System.out.println(user);
            }

        } catch(NoUsersFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeUser(int userID) {
        AdminDao dao = new AdminDaoImpl();
        try {
            dao.removeUser(userID);
        } catch(UserNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void importUsers(String filepath){
        // Import users from a json file and then add them to the database

        File file = new File(filepath);
        AdminDao dao = new AdminDaoImpl();
    }
}
