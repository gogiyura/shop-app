package com.example.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {



    private ConnectionPool(){
        //private constructor
    }

    private static DataSource instance;

    public static DataSource getInstance(){
        if (instance==null)
        {
            /*HikariConfig config = new HikariConfig("hikaricp.properties");
            //config.setJdbcUrl();
            HikariDataSource ds = new HikariDataSource();*/

            instance = DBUtil.getDataSource();
        }
        return instance;
    }

    public Connection getConnection(){
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/shop-app");
            c = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
