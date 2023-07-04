package com.example.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn (name = "pedido_id")
    private Pedido pedido;
}
