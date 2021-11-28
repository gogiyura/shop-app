package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {

    public static boolean isPhoneNumber(String s) {
        return s.matches("(\\+*)\\d{11}");
    }
    public static boolean isEmail(String s) {
        return s.matches("/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/");
    }

    public static void closeCon(Connection con) throws SQLException {
        con.close();
    }

    public static void closePst(PreparedStatement spt) throws SQLException {
        spt.close();
    }

    public static void closeRSet(ResultSet rs) throws SQLException {
        rs.close();
    }
}
