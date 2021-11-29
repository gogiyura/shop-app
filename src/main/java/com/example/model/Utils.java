package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {

    public static String loginProcess(String s) {
        int l = s.length();
        if(isEmail(s)) {
            return "email";
        } else if(isPhoneNumber(s)) {
            return "phoneNumber";
        } else {
            return "err";
        }
    }

    public static boolean isPhoneNumber(String s) {
        return s.matches("^(\\+)?((\\d{2,3}) ?\\d|\\d)(([ -]?\\d)|( ?(\\d{2,3}) ?)){5,12}\\d$");
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
