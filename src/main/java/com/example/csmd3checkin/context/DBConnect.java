package com.example.csmd3checkin.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/manager_employees";
    private final String userID = "root";
    private final String password = "1212";

    public Connection getConnection() throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, userID, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
        //mjasgdkjasdagkuacglsievkvuksaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    }
//    aiuduacbkjacbkajsaaaaaaaaaaaaaaaaaaaaaaaaaaaaabdkasdasdasd
}

