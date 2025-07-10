package com.demo.mi_cafeteria.model.responses;

public class AuthResponse {
    private String token;
    private String error;


    public AuthResponse(String token,String error) {
        this.token=token;
        this.error=error;

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class RegistryRequest {
        private String nombreUsuario;
        private String apPaternoUsuario;
        private String emailUsuario;
        private String nickname;
        private String pwdUsuario;

        public RegistryRequest(String nombreUsuario, String apPaternoUsuario, String emailUsuario, String nickname, String pwdUsuario) {
            this.nombreUsuario = nombreUsuario;
            this.apPaternoUsuario = apPaternoUsuario;
            this.emailUsuario = emailUsuario;
            this.nickname = nickname;
            this.pwdUsuario = pwdUsuario;
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
    }
}
