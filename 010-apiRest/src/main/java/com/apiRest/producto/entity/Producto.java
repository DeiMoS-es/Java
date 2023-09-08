package com.apiRest.producto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * con la anotación @Builder, conseguimos construir los objetos de manera inmutable y de una forma,
 * más clara a la hora de leer el código
 * // Uso
 * Producto producto = Producto.builder()
 *                             .id(1L)
 *                             .nombre("Producto A")
 *                             .precio(19.99)
 *                             .build();
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @NotNull
    @NotEmpty
    @NotBlank
    private String nombreProducto;

    @NotNull
    @NotEmpty
    @NotBlank
    private Double precioProducto;

    @NotNull
    @NotEmpty
    @NotBlank
    private Double ivaProducto;

    private LocalDateTime fechaAlta;

}
