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
    @JoinColumn(name = "id_producto") // Aquí si nos interesa que nos muestre los productos del pedido
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    @JsonIgnore // Añadimos esta notación para no serializar los detalles del pedido cuando busquemos un pedido, ya que no nos interesa
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

