package com.example.model.entity;

import com.example.model.Utils;

import java.sql.Date;

public class User extends Entity {

    private String firstName;
    private String lastName;
    private Role role;
    private boolean blocked;
    private Date birthDate;
    private Date createTime;

    private String phoneNumber;
    private String email;
    private String password;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User(String login, String password) {

    }

    public User(String login, String password, int n){
        if(n == 1) {
            this.email = login;
        } else if(n == 2) {
            this.phoneNumber = login;
        }
        this.password = password;

    }

    public User(){}
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public enum Role {
        ADMIN, USER;

        public boolean isUser() {
            return this == USER;
        }
        public boolean isAdmin() {
            return this == ADMIN;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}