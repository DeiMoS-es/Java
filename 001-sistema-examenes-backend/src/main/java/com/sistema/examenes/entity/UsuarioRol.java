package com.sistema.examenes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolId;

    //Fetch.EAGER->ansioso, por ejemplo, listamos un usuario,
    // y quieres que devuelva los datos relacionados, como los roles,
    // lanzará todo los registros relacionados
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    //Muchos usuarios pertenecen a un rol
    @ManyToOne
    private Rol rol;

    public UsuarioRol() {
    }
}
