package com.healthcare.framework;

import com.healthcare.framework.db.DatabaseManager;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DatabaseConnectionTest {

    @Test
    public void canConnectToDatabase() throws Exception {
        Connection connection = DatabaseManager.getConnection();
        System.out.println("Connected: " + !connection.isClosed());
        DatabaseManager.closeConnection();
    }
}