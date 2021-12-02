package com.example.model.bean;

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