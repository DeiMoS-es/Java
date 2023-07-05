package com.example.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @NotNull
    private String nombreProducto;

    private LocalDate fechaAlta;

    private Double precioProducto;

    private Double ivaProducto;

    private String imgProducto;

    @NotNull
    private Double cantidadProducto;

    @ManyToMany(mappedBy = "productos")
    private List<Pedido> pedidos;
}
