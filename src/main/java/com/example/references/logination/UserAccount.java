package com.example.references.logination;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {

    public static final String GENDER_MALE ="M";
    public static final String GENDER_FEMALE = "F";

    private String email;
    private String name;
    private String password;
    private List<String> roles = getRoles();
    private boolean blocked = false;


    public UserAccount() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void switchBlocking(){
        this.blocked = !blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public UserAccount(String userName, String password, String gender, String... roles) {
        this.name = userName;
        this.password = password;


        this.roles = new ArrayList<String>();
        if (roles != null) {
            for (String r : roles) {
                this.roles.add(r);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}