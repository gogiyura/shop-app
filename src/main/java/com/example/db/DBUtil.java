package com.example.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBUtil {
    private static final String DB_USERNAME="db.username";
    private static final String DB_PASSWORD="db.password";
    private static final String DB_URL ="db.url";

    private static Properties properties = null;
    private static MysqlDataSource dataSource;
    static{
        try {
            properties = new Properties();
            properties.load(new FileInputStream("../resources/hikaricp.properties"));

            dataSource = new MysqlDataSource();
            dataSource.setUrl(properties.getProperty(DB_URL));
            dataSource.setUser(properties.getProperty(DB_USERNAME));
            dataSource.setPassword(properties.getProperty(DB_PASSWORD));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Utility method returns JDBC connection object
    public static DataSource getDataSource(){
        return dataSource;
    }
}