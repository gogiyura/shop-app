package com.example.model;

import com.example.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterDAO {
    public static int register(User u){
        int status=0;
        try{
            ConnectionPool cp = ConnectionPool.getInstance();
            Connection con = cp.getConnection();
            PreparedStatement ps=con.prepareStatement("insert into user432 values(?,?,?)");
            ps.setString(1,u.getEmail());
            ps.setString(2,u.getPassword());
            ps.setString(3,u.getPhoneNumber());
            status=ps.executeUpdate();
        }catch(Exception e){}

        return status;
    }
}
