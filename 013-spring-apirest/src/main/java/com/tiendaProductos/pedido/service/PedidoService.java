package com.tiendaProductos.pedido.service;

import com.tiendaProductos.pedido.entity.Pedido;
import com.tiendaProductos.producto.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoService {
    void guardarPedido(Long idUsuario ,List<ProductoDTO> listaProductos);
    Pedido buscarPedidoPorId(Long idPedido);
    boolean eliminarPedido(Long idPedido);
}
