package com.demo.mi_cafeteria.model.dto;

import com.demo.mi_cafeteria.model.entity.UsuarioInfo;

public class UsuarioDto {

    private Integer usuarioInfoId;
    private String nombreUsuario;
    private String apellidoPaternoUsuario;
    private String apellidoMaternoUsuario;
    private String email;

    public UsuarioDto(UsuarioInfo usuarioInfo) {
        this.usuarioInfoId= usuarioInfo.getUsuarioInfoId();
        this.nombreUsuario= usuarioInfo.getNombreUsuario();
        this.apellidoPaternoUsuario=usuarioInfo.getApellidoPaternoUsuario();
        this.apellidoMaternoUsuario=usuarioInfo.getApellidoMaternoUsuario();
        this.email=usuarioInfo.getEmail();
    }

    public UsuarioDto(){}

    public Integer getUsuarioInfoId() {
        return usuarioInfoId;
    }

    public void setUsuarioInfoId(Integer usuarioInfoId) {
        this.usuarioInfoId = usuarioInfoId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPaternoUsuario() {
        return apellidoPaternoUsuario;
    }

    public void setApellidoPaternoUsuario(String apellidoPaternoUsuario) {
        this.apellidoPaternoUsuario = apellidoPaternoUsuario;
    }

    public String getApellidoMaternoUsuario() {
        return apellidoMaternoUsuario;
    }

    public void setApellidoMaternoUsuario(String apellidoMaternoUsuario) {
        this.apellidoMaternoUsuario = apellidoMaternoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
