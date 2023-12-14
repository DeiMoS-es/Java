package com.tiendaProductos.pedido.service.impl;

import com.tiendaProductos.pedido.entity.Pedido;
import com.tiendaProductos.pedido.service.PedidoService;
import com.tiendaProductos.producto.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PedidoServiceImpl implements PedidoService {
    //TODO implementar métodos
    @Override
    public void guardarPedido(List<ProductoDTO> listaProductos) {

    }

    @Override
    public Pedido buscarPedidoPorId(Long idPedido) {
        return null;
    }

    @Override
    public ResponseEntity<?> eliminarPedido(Long idPedido) {
        return null;
    }
}
