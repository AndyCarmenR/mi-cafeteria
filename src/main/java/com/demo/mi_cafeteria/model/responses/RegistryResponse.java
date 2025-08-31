package com.demo.mi_cafeteria.model.responses;

import com.demo.mi_cafeteria.model.dto.UsuarioDto;

public class RegistryResponse {
    private UsuarioDto usuarioInfo;

    private String mensaje;

    public RegistryResponse(UsuarioDto usuarioInfo, String mensaje) {
        this.usuarioInfo = usuarioInfo;
        this.mensaje = mensaje;
    }

    public RegistryResponse() {
    }

    public UsuarioDto getUsuarioInfo() {
        return usuarioInfo;
    }

    public void setUsuarioInfo(UsuarioDto usuarioInfo) {
        this.usuarioInfo = usuarioInfo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
