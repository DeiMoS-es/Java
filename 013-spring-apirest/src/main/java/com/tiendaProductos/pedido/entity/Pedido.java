package com.tiendaProductos.pedido.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiendaProductos.producto.entity.Producto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    //TODO cuando esté listo la creación del pedido, añadir que el pedido se asocie a un solo Usuario

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @NotNull
    private LocalDateTime fechaPedido;

    @NotNull
    private String estadoPedido;

    @NotNull
    private Double precioTotal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallesPedido;

    @ManyToMany
    @JoinTable(name = "producto_pedido", joinColumns = @JoinColumn(name = "id_pedido"), inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<Producto> productos;

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", fechaPedido=" + fechaPedido +
                ", estadoPedido='" + estadoPedido + '\'' +
                // Evita la referencia circular a DetallesPedido aquí
                // Evita la referencia circular a Productos aquí
                '}';
    }
}

