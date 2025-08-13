package com.demo.mi_cafeteria.model.requests;

import java.util.List;

public class RegistryRequest {

    private String nombreUsuario;
    private String apPaternoUsuario;
    private String emailUsuario;
    private String nickname;
    private String pwdUsuario;
    private List<Integer> listRolId;
    private Boolean isUpdating;

    public RegistryRequest(String nombreUsuario, String apPaternoUsuario, String emailUsuario, String nickname, String pwdUsuario, List<Integer> listRolId,Boolean isUpdating) {
        this.nombreUsuario = nombreUsuario;
        this.apPaternoUsuario = apPaternoUsuario;
        this.emailUsuario = emailUsuario;
        this.nickname = nickname;
        this.pwdUsuario = pwdUsuario;
        this.listRolId =listRolId;
        this.isUpdating=isUpdating;
    }

    public RegistryRequest() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApPaternoUsuario() {
        return apPaternoUsuario;
    }

    public void setApPaternoUsuario(String apPaternoUsuario) {
        this.apPaternoUsuario = apPaternoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwdUsuario() {
        return pwdUsuario;
    }

    public void setPwdUsuario(String pwdUsuario) {
        this.pwdUsuario = pwdUsuario;
    }

    public List<Integer> getListRolId() {
        return listRolId;
    }

    public void setListRolId(List<Integer> listRolId) {
        this.listRolId = listRolId;
    }

    public boolean isUpdating() {
        return this.isUpdating;
    }

    public void setUpdating(boolean updating) {
        this.isUpdating = updating;
    }
}