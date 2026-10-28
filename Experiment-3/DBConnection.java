package com.studentcrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to obtain a JDBC connection to MySQL.
 *
 * ⚠ Update the URL, username, and password to match your local setup.
 */
public class DBConnection {

    private static final String JDBC_URL      = "jdbc:mysql://localhost:3306/studentDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "password";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load MySQL JDBC Driver (supports modern com.mysql.cj.jdbc.Driver and legacy fallback)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                Class.forName("com.mysql.jdbc.Driver");
            }
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
