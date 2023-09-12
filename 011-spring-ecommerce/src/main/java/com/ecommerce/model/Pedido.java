package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Integer idPedido;
    private String numero;
    private Date fechaCreacion;
    private Date fechaRecibida;
    private Double total;
}
