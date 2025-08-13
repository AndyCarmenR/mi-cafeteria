package com.demo.mi_cafeteria.model.responses;

import com.demo.mi_cafeteria.model.dto.RolDto;

public class CreateRoleResponse {
    private RolDto rol;
    private String mensaje;

    public RolDto getRol() {
        return rol;
    }

    public void setRol(RolDto rol) {
        this.rol = rol;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
