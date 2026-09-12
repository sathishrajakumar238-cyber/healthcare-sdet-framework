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

    public static int getAppointmentCountForPatient(int patientId) throws SQLException {
    String sql = "SELECT COUNT(e.pc_eid) AS total_appointments " +
                 "FROM patient_data p " +
                 "JOIN openemr_postcalendar_events e ON p.pid = e.pc_pid " +
                 "WHERE p.pid = ? " +
                 "GROUP BY p.pid";

    try (var statement = getConnection().prepareStatement(sql)) {
        statement.setInt(1, patientId);
        var resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("total_appointments");
        }
        return 0;
    }
}
}