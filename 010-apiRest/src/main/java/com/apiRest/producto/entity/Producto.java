package com.apiRest.producto.entity;

import com.apiRest.pedido.entity.Pedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private Double precioProducto;

    @NotNull
    private Double ivaProducto;

    @NotNull
    private Integer stockProducto;

    private LocalDateTime fechaAlta;

    @ManyToMany(mappedBy = "productos")
    private List<Pedido> pedidos;

}
