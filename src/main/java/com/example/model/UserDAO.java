package com.example.model;

import com.example.model.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static DataSource connectionPool = ConnectionPool.getInstance();
    private static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }
    public static int save(User u){
        int status=0;
        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("insert into user(password,email) values(?,?)");
            ps.setString(1,u.getPassword());
            ps.setString(2,u.getEmail());
            status=ps.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        return status;
    }
    public static int update(User u){
        int status=0;
        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("update user set password=?,email=?, where id=?");
            ps.setString(1,u.getPassword());
            ps.setString(2,u.getEmail());
            ps.setString(3,String.valueOf(u.getId()));
            status=ps.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        return status;
    }
    public static int delete(User u){
        int status=0;
        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("delete from user where id=?");
            ps.setLong(1,u.getId());
            status=ps.executeUpdate();
        }catch(Exception e){System.out.println(e);}

        return status;
    }
    public static List<User> getAllRecords(){
        List<User> list=new ArrayList<User>();

        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("select * from user");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                User u=new User();
                u.setId(rs.getInt("id"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                list.add(u);
            }
        }catch(Exception e){System.out.println(e);}
        return list;
    }


    public static User getRecordById(int id){
        User u=null;
        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("select * from user where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                u=new User();
                u.setId(rs.getInt("id"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
            }
        }catch(Exception e){System.out.println(e);}
        return u;
    }

}
