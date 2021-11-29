package com.example.model.logination;

import java.util.*;

public class SecurityConfig {
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {

        // Конфигурация для роли "USER".
        List<String> urlPatterns1 = new ArrayList<String>();

        urlPatterns1.add("/employee");
        urlPatterns1.add("/cabinet");
        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/employeeTask");

        mapConfig.put(ROLE_USER, urlPatterns1);

        // Конфигурация для роли "ADMIN".
        List<String> urlPatterns2 = new ArrayList<String>();

        urlPatterns2.add("/employee");
        urlPatterns2.add("/control");
        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/managerTask");

        mapConfig.put(ROLE_ADMIN, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
