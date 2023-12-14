package com.tiendaProductos.pedido.service;

import com.tiendaProductos.pedido.entity.Pedido;
import com.tiendaProductos.producto.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoService {
    void guardarPedido(List<ProductoDTO> listaProductos);
    Pedido buscarPedidoPorId(Long idPedido);
    ResponseEntity<?> eliminarPedido(Long idPedido);
}
