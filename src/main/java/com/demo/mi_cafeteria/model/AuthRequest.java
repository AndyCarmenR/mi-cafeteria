package com.demo.mi_cafeteria.model;

public class AuthRequest {
    private String nickName;
    private String password;

    public String getUsername() {
        return nickName;
    }

    public void setUsername(String username) {
        this.nickName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
