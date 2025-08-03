package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

@Entity(name = "login")
public class Login {

    public Login(Integer loginID, UsuarioPWD usuarioPWD, String token) {
        this.loginID = loginID;
        this.usuarioPWD = usuarioPWD;
        this.token = token;
    }

    public Login() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOGIN_ID")
    private Integer loginID;

    @OneToOne
    @JoinColumn(name = "USUARIO_PWD_ID")
    private UsuarioPWD usuarioPWD;

    @Column(name = "TOKEN")
    private String token;

    public Integer getLoginID() {
        return loginID;
    }

    public void setLoginID(Integer loginID) {
        this.loginID = loginID;
    }

    public UsuarioPWD getUsuarioPWD() {
        return usuarioPWD;
    }

    public void setUsuarioPWD(UsuarioPWD usuarioPWD) {
        this.usuarioPWD = usuarioPWD;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
