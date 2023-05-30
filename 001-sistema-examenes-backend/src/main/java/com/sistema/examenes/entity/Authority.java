package com.sistema.examenes.entity;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    //Básicamente este método e spara obtener un "rol"
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
