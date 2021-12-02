package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class RequestSQL {
    public static final String SELECT = "select";
    public static final String DELETE = "select";
    public static final String UPDATE = "select";
    public static final String INSERT = "select";



    public static String count(String table){
        return String.format("SELECT COUNT(*) FROM %s", table);
    }


    public static String makeQuery(String typeOfQuery, String table,
                                   String condition, String orderRule, boolean reverse) {

        Map<String, String> action = new HashMap<>();
        action.put(SELECT, "SELECT * FROM %s ");
        action.put(DELETE, "DELETE FROM %s ");
        action.put(UPDATE, "UPDATE %s SET ");
        action.put(INSERT, "INSERT INTO %s ");

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(action.get(typeOfQuery), table));

        if (action.equals(UPDATE)) {
            sb.append(String.format("%s ", orderRule));
        }

        if(!action.equals(INSERT)) {
            sb.append(String.format("WHERE %s ", condition));
        } else {
            sb.append(String.format("%s VALUE%s", condition, orderRule));
        }

        if (action.equals(SELECT)) {
            sb.append(String.format("ORDER BY %s ", orderRule));
            sb.append(reverse ? "DESC" : "");
        }

        return sb.toString();
    }

    public static String makeQuery(String typeOfQuery, String table,
                                   String condition, String orderRule){
        return makeQuery(typeOfQuery, table, condition, orderRule, false);
    }


}
