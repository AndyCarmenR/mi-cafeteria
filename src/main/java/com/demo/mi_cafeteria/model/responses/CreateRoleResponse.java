package com.demo.mi_cafeteria.model.responses;

import com.demo.mi_cafeteria.model.entity.Roles;

public class CreateRoleResponse {
    private Roles rol;
    private String mensaje;

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
