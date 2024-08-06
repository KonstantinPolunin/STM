package com.stm.tickets.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String userName = "postgres";
    private static final String password = "postgres";


    public static Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, userName, password);
            System.out.println("Connection is OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
