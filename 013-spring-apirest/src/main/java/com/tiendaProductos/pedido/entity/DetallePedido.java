package com.tiendaProductos.pedido.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiendaProductos.producto.entity.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    private Integer cantidad;

    @Override
    public String toString() {
        return "DetallePedido{" +
                "idDetalle=" + idDetalle +
                ", cantidad=" + cantidad +
                // Evita la referencia circular a Producto aquí
                // Evita la referencia circular a Pedido aquí
                '}';
    }

}

