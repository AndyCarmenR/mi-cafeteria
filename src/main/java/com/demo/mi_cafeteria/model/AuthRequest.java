package com.demo.mi_cafeteria.model;

public class AuthRequest {
    private String nickName;
    private String password;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String username) {
        this.nickName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
