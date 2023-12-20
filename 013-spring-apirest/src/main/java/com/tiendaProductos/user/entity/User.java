package com.tiendaProductos.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"userName"})}) // Indicamos que la columna userName no puede repetirse el valor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @Column(nullable = false) // no permite insert de valores null
    private String userName;
    private String password;
    private String nombre;
    private String apellidos;
    private String pais;
    Role rol;
}
