package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalles")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetallePedido;
    private String nombre;
    private double cantidad;
    private double precio;
    private double total;

    @OneToOne
    private Pedido pedido;
    /**
     * Relación con productos, en producto no es necesario conocer el detalle del pedido
     */
    @ManyToOne
    private Producto producto;
}
