package com.tiendaProductos.pedido.service;

import com.tiendaProductos.producto.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoService {
    ResponseEntity<?> guardarPedido(List<ProductoDTO> listaProductos);
    ResponseEntity<?> buscarPedidoPorId(Long idPedido);
    ResponseEntity<?> eliminarPedido(Long idPedido);
}
