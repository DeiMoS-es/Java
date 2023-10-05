package com.apiRest.pedido.entity;

import com.apiRest.detallePedido.entity.DetallePedido;
import com.apiRest.producto.entity.Producto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @NotNull
    private LocalDateTime fechaPedido;

    @NotNull
    private String estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallesPedido;

    @ManyToMany
    @JoinTable(name = "producto_pedido", joinColumns = @JoinColumn(name = "id_pedido"), inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<Producto> productos;

    //TODO cuando termine todo lo relacionado con el pedido, tendré que añadir el sistema de usuarios, roles y autenticación y autorización

}
