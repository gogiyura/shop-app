package com.example;


import com.example.db.ConnectionPool;

import com.example.model.ProductController;
import com.example.model.ProductSpecification;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ResourceBundle;

public class Main {



    public static void main(String[] args) throws Exception {

        ResourceBundle bundle = ResourceBundle.getBundle("shop");


        DataSource ds = ConnectionPool.getInstance();
        System.out.println(ds);

        System.out.println(new ProductController().
                query(new ProductSpecification()));

        Connection c = ds.getConnection();
        System.out.println(c);
        String sql = "SELECT * FROM PRODUCT;";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            for (int i = 1; i < 4; i++) {
                System.out.print(rs.getString(i));
            }
            System.out.print("\n");
        }
        rs.close();
        st.close();
        c.close();
    }



}
