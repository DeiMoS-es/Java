package com.example.jwt.spring3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioRolId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    //Muchos usuarios pertenecen a un rol
    @ManyToOne
    private Rol rol;

    @Override
    public String toString() {
        return "UsuarioRol{" +
                "usuarioRolId=" + usuarioRolId +
                ", usuario=" + usuario +
                ", rol=" + rol +
                '}';
    }
}
