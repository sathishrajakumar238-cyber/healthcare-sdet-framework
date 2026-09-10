package com.healthcare.framework.db;

import com.healthcare.framework.config.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {

            String url = ConfigManager.get().getProperty("db.url");
            String username = ConfigManager.get().getProperty("db.username");
            String password = ConfigManager.get().getProperty("db.password");

            connection = DriverManager.getConnection(url, username, password);
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}