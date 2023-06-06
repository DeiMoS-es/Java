package com.example.jwt.spring3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rolId;
    private String nombreRol;
    //Un rol pertenece a muchos usuarios
    //Fetc->perezoso, para obtener las características/atributos hay que indicarlo
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    public Rol() {
    }

    public Rol(Integer rolId, String nombreRol, Set<UsuarioRol> usuarioRoles) {
        this.rolId = rolId;
        this.nombreRol = nombreRol;
        this.usuarioRoles = usuarioRoles;
    }
}
