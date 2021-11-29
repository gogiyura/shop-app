package com.example;


import com.example.model.ConnectionPool;
import com.example.model.ProductDAO;
import com.example.model.Utils;

import javax.sql.DataSource;
import java.sql.*;

public class Main {



    public static void main(String[] args) throws Exception {
        DataSource ds = ConnectionPool.getInstance();
        System.out.println(ds);

        System.out.println(ProductDAO.getAllProducts());
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
        Utils.closeRSet(rs);
        st.close();
        c.close();
    }



}
