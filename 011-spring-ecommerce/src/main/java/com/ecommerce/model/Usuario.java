package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private Integer idUsuario;
    private String nombre;
    private String userName;
    private String email;
    private String direccion;
    private String telefono;
    //tipo es el rol de usuario
    private String tipo;
    private String password;
}
