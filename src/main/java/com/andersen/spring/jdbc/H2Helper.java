package com.andersen.spring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Helper {

    private Connection connection;

    public H2Helper()
    {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection()
    {
        return connection;
    }
}
