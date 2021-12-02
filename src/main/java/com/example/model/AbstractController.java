package com.example.model;

import com.example.db.DBManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

abstract public class AbstractController{
    private static AbstractController instance;

    public static AbstractController getInstance() {
        return instance;
    }

    private Connection connection;
    private DBManager dbManager;

    public AbstractController() {
        dbManager = DBManager.getInstance();
    }
    public Connection getConnection() throws SQLException {

        InitialContext initContext= null;
        DataSource ds = null;
        try {
            initContext = new InitialContext();
            ds = (DataSource) initContext.lookup("java:comp/env/jdbc/shop-app");

        } catch (NamingException e) {
            e.printStackTrace();
            //TODO залогировать
        }
        Connection connection = ds.getConnection();
        return connection;
    }
    public PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    public void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
