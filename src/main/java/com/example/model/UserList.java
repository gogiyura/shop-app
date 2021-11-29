package com.example.model;

import com.example.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static List<User> userList = new ArrayList<>();

    static {
        userList.addAll(UserDAO.getAllRecords());
    }
}
