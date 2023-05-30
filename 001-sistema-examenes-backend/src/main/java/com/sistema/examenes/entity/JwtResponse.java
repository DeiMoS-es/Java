package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

//Cuando genero la respuesta
@Getter
@Setter
public class JwtResponse {

    private String token;

    public JwtResponse() {
    }
    public JwtResponse(String token) {
        this.token = token;
    }
}
