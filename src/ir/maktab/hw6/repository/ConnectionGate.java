package ir.maktab.hw6.repository;

import java.sql.*;

public class ConnectionGate {
    private static final String URL = "jdbc:postgresql://localhost/hw6";
    private static final String USER = "postgres";
    private static final String PASSWORD = "4600099941";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL, USER, PASSWORD
        );
    }
}
