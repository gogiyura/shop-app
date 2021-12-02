package com.example.references.logination.trash;

import com.example.db.ConnectionProvider;
import com.example.model.bean.Account;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterDAO {
    public static int register(Account u){
        int status=0;
        try{
            Connection con= ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("insert into user(email, password) values(?,?)");
            //ps.setString(1,u.getEmail());
            //ps.setString(2,u.getPassword());

            status=ps.executeUpdate();
        }catch(Exception e){}

        return status;
    }
}
