package com.example.db;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {

    public static Connection getCon() throws SQLException {
        return ConnectionPool.getInstance().getConnection();
    }
}
