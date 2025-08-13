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


}
