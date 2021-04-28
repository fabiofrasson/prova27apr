package com.example.Prova27APR.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/prova27apr", "root", "");
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }
}
