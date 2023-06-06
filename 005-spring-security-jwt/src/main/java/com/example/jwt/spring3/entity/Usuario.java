package com.example.jwt.spring3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String userName;
    private String password;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String email;
    private Date fechaAlta;
    private boolean enable = true;
    private String perfil;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", enable=" + enable +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}
