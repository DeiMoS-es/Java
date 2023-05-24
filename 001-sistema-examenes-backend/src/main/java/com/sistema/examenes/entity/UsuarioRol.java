package com.sistema.examenes.entity;

import jakarta.persistence.*;

@Entity
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolId;

    //Fetch.EAGER->ansioso, por ejemplo, listamos un usuario,
    // y quieres que devuelva los datos relacionados, como los roles,
    // lanzará todo los registros relacionados
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    @ManyToOne
    private Rol rol;
}
