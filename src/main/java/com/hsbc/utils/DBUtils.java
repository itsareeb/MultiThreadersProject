package com.hsbc.utils;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    public static Connection conn = null;
    static String url = "jdbc:mysql://localhost:3306/HospitalMgmtSystem";

    public static Connection getConnection() {
        if (conn == null) {
            try {
                DriverManager.registerDriver(new Driver());
                // TODO : Change the username and password as per your MySQL installation
                conn = DriverManager.getConnection(url, "root", "sql123");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        } else
            return conn;
    }
}
