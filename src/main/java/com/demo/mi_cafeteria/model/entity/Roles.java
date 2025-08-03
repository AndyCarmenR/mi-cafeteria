package com.demo.mi_cafeteria.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_ID")
    private Integer rolId;

    @Column(name="NOMBRE_ROL")
    private String nombreRol;

    @Column(name = "DESCRIPCION_ROL")
    private String descripcionRol;

    @ManyToMany(mappedBy = "roles")
    private Set<UsuarioInfo> usuarios = new HashSet<>();

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public Set<UsuarioInfo> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioInfo> usuarios) {
        this.usuarios = usuarios;
    }
}
