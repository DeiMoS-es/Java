package com.tiendaProductos.producto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Column(unique = true)
    private String nombreProducto;
    @NotBlank(message = "La descripción del producto no puede estar vacía")
    private String descripcionProducto;
    @NotNull(message = "El precio del producto no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private Double precioProducto;
    @NotNull(message = "El stock del producto no puede ser nulo")
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Integer stockProducto;
    @NotBlank(message = "El tipo del producto no puede estar vacío")
    private String tipoProducto;
    //Este campo se asignará automaticamente al dar de alta un producto
    private LocalDateTime fechaAltaProducto;
}
