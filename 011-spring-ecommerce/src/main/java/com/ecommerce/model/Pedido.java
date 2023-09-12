package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;
    private String numero;
    private Date fechaCreacion;
    private Date fechaRecibida;
    private Double total;

    /**
     * Un usuario puede tener varios pedidos
     */
    @ManyToOne
    private Usuario usuario;
    /**
     * relacion con detalle pedido
     */
    @OneToOne(mappedBy = "pedido")
    private DetallePedido detallePedido;
}
