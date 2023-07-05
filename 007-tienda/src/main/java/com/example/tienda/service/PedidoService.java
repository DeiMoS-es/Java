package com.example.tienda.service;

import com.example.tienda.entity.Pedido;
import com.example.tienda.entity.Usuario;

public interface PedidoService {
    void guardarPedido(Pedido pedido);
    Pedido buscarPedidoPorId(Long idPedido);
    void eliminarPedido(Long idPedido);
}
