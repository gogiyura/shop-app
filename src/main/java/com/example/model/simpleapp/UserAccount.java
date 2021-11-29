package com.example.model.simpleapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccount {

    public static final String GENDER_MALE ="M";
    public static final String GENDER_FEMALE = "F";

    private String userName;
    private String gender;
    private String password;
    private List<String> roles = getRoles();
    private boolean blocked = false;


    public UserAccount() {

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
        this.userName = userName;
        this.password = password;
        this.gender = gender;


        this.roles = new ArrayList<String>();
        if (roles != null) {
            for (String r : roles) {
                this.roles.add(r);
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getLogin() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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