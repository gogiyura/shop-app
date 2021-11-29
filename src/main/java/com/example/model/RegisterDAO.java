package com.example.model;

import com.example.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterDAO {
    public static int register(User u){
        int status=0;
        try{
            Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("insert into user(email, password) values(?,?)");
            ps.setString(1,u.getEmail());
            ps.setString(2,u.getPassword());

            status=ps.executeUpdate();
        }catch(Exception e){}

        return status;
    }
}
