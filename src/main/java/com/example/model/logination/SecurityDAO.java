package com.example.model.logination;


import com.example.model.simpleapp.UserAccount;

import java.util.HashMap;
import java.util.Map;

public class SecurityDAO {
    private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();

    static {
        initUsers();
    }

    private static void initUsers() {

/*        // This user has a role as EMPLOYEE.
        UserAccount emp = new UserAccount("employee1", "123",
                SecurityConfig.ROLE_USER);

        // This user has 2 roles EMPLOYEE and MANAGER.
        UserAccount mng = new UserAccount("manager1", "123",
                SecurityConfig.ROLE_USER, SecurityConfig.ROLE_ADMIN);

        mapUsers.put(emp.getLogin(), emp);
        mapUsers.put(mng.getLogin(), mng);
    }*/

    // Find a User by userName and password.
/*    public static UserAccount findUser(String userName, String password) {
        UserAccount u = mapUsers.get(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
 */   }
}
