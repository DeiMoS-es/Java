package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private String imagen;
    private double precio;
    private int cantidad;
}
