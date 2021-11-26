package com.example.model;

import java.util.regex.Pattern;

public class Utils {

    public static boolean isPhoneNumber(String s) {
        return s.matches("(\\+*)\\d{11}");
    }
    public static boolean isEmail(String s) {
        return s.matches("/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/");
    }

}
