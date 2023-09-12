package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private String imagen;
    private double precio;
    private int cantidad;
    /**
     * Este campo se relaciona con la tabla de usuarios
     * En este caso es una relación de Muchos a uno
     */
    @ManyToOne
    private Usuario usuario;
}
