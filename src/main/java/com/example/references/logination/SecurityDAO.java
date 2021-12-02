package com.example.references.logination;


import com.example.model.bean.Account;

import java.util.HashMap;
import java.util.Map;

public class SecurityDAO {


    private static final Map<String, Account> mapUsers = new HashMap<String, Account>();

    static {
        initUsers();
    }

    private static void initUsers() {

        // This user has a role as EMPLOYEE.
/*        Account emp = new Account();
        "employee", "pass", SecurityConfig.ROLE_USER);*/

        // This user has 2 roles EMPLOYEE and MANAGER.
        Account mng = new Account();

        //("manager", "pass", SecurityConfig.ROLE_USER, SecurityConfig.ROLE_ADMIN);

/*        mapUsers.put(emp.getLogin(), emp);
        mapUsers.put(mng.getLogin(), mng);*/
    }

    // Find a User by userName and password.

    public static Account findUser(String userName, String password) {
        Account u = mapUsers.get(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}
