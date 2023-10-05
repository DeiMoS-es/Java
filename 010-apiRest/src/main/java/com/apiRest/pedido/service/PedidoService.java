package com.apiRest.pedido.service;

import com.apiRest.pedido.entity.Pedido;
import com.apiRest.producto.dto.ProductoDTO;
import com.apiRest.producto.entity.Producto;

import java.util.List;

public interface PedidoService {
    void guardarPedido(List<ProductoDTO> listaProductos);
}
