package com.demo.mi_cafeteria.model.responses;

import com.demo.mi_cafeteria.model.UsuarioInfo;
import com.demo.mi_cafeteria.model.UsuarioPWD;

public class RegistryResponse {
    private UsuarioInfo usuarioInfo;
    private UsuarioPWD usuarioPWD;
    private String mensaje;

    public RegistryResponse(UsuarioInfo usuarioInfo, UsuarioPWD usuarioPWD, String mensaje) {
        this.usuarioInfo = usuarioInfo;
        this.usuarioPWD = usuarioPWD;
        this.mensaje = mensaje;
    }

    public RegistryResponse() {
    }

    public UsuarioInfo getUsuarioInfo() {
        return usuarioInfo;
    }

    public void setUsuarioInfo(UsuarioInfo usuarioInfo) {
        this.usuarioInfo = usuarioInfo;
    }

    public UsuarioPWD getUsuarioPWD() {
        return usuarioPWD;
    }

    public void setUsuarioPWD(UsuarioPWD usuarioPWD) {
        this.usuarioPWD = usuarioPWD;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
