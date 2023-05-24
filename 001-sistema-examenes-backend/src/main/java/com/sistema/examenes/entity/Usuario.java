package com.sistema.examenes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private boolean enable = true;
    private String perfil;
    //Un usuario puede tener MUCHOS roles
    //mappedBy -> apunta a la entidad propietaria de la relación,
    //especifica claves ajenas en la relación
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    public Usuario() {
    }
}
