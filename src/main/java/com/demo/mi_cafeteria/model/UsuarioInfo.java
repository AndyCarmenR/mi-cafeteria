package com.demo.mi_cafeteria.model;

import jakarta.persistence.*;

@Entity(name = "usuarios_info")
public class UsuarioInfo {

    public UsuarioInfo(Integer usuarioInfoId, String nombreUsuario, String apellidoPaternoUsuario, String apellidoMaternoUsuario, String email) {
        this.usuarioInfoId = usuarioInfoId;
        this.nombreUsuario = nombreUsuario;
        this.apellidoPaternoUsuario = apellidoPaternoUsuario;
        this.apellidoMaternoUsuario = apellidoMaternoUsuario;
        this.email = email;
    }

    public UsuarioInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USUARIO_INFO_ID")
    private Integer usuarioInfoId;

    @Column(name="NOMBRE_USUARIO")
    private String nombreUsuario;

    @Column(name = "APELLIDO_PATERNO_USUARIO")
    private String apellidoPaternoUsuario;

    @Column(name = "APELLIDO_MATERNO_USUARIO")
    private String apellidoMaternoUsuario;

    @Column(name = "EMAIL")
    private String email;

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
