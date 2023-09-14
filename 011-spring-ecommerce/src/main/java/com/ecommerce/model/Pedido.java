package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
//Me obliga a añadir esta anotación para poder inyectar pedidos en los controladores
//Con esta anotación Spring gestionará el ciclo de vida de los Pedidos y creará instancias cuando crea necesario, según se haga uso de la clase en la aplicación
@Component
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
