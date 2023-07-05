package com.example.tienda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rolId;

    private String nombreRol;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    // ... otros métodos y constructores

    // Método para agregar un usuario al rol
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        usuario.getRoles().add(this);
    }
}
