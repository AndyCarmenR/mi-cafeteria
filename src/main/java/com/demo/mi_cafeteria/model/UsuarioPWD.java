package com.demo.mi_cafeteria.model;

import jakarta.persistence.*;

@Entity(name = "usuarios_pwd")
public class UsuarioPWD {

    public UsuarioPWD(Long id, UsuarioInfo usuarioInfo, String nickname, String password) {
        this.id = id;
        this.usuarioInfo = usuarioInfo;
        this.nickname = nickname;
        this.password = password;
    }

    public UsuarioPWD() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USUARIO_PWD_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USUARIO_INFO_ID")
    private UsuarioInfo usuarioInfo;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "PWRD")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioInfo getUsuarioInfo() {
        return usuarioInfo;
    }

    public void setUsuarioInfo(UsuarioInfo usuarioInfo) {
        this.usuarioInfo = usuarioInfo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
