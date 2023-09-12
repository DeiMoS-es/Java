package com.apiRest.pedido.entity;

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

    /**
     * Establece una relación muchos a muchos entre los pedidos y los productos.
     * Utiliza una tabla de unión llamada "pedido_producto" para mantener las relaciones.
     *
     * @ManyToMany indica que un pedido puede tener varios productos y un producto puede estar en varios pedidos.
     * @JoinTable se utiliza para especificar el nombre de la tabla de unión y cómo se relacionarán las columnas.
     * - name = "pedido_producto" define el nombre de la tabla de unión en la base de datos.
     * - joinColumns especifica cómo se mapearán las columnas de la entidad "Pedido" en la tabla de unión.
     * - inverseJoinColumns especifica cómo se mapearán las columnas de la entidad "Producto" en la tabla de unión.
     *
     * @return Una lista de productos asociados a este pedido.
     */
    @ManyToMany
    @JoinTable(name = "pedido_producto", joinColumns = @JoinColumn(name = "id_pedido"), inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<Producto> productos;

    //TODO cuando termine todo lo relacionado con el pedido, tendré que añadir el sistema de usuarios, roles y autenticación y autorización

}
