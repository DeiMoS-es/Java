package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

//Cuando hacemos una petición
@Getter
@Setter
public class JwtRequest {

    private String username;
    private String password;

    public JwtRequest() {
    }
    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
