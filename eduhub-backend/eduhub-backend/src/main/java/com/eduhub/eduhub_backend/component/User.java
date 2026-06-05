package com.eduhub.eduhub_backend.component;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int userId;
    private String userName;
    private String Password;

    public User() {
    }

    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        Password = password;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
