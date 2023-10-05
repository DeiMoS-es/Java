package com.apiRest.pedido.service.impl;

import com.apiRest.detallePedido.entity.DetallePedido;
import com.apiRest.pedido.entity.Pedido;
import com.apiRest.pedido.repository.PedidoRepository;
import com.apiRest.pedido.service.PedidoService;
import com.apiRest.producto.dto.ProductoDTO;
import com.apiRest.producto.entity.Producto;
import com.apiRest.producto.repository.ProductoRepository;
import com.apiRest.producto.service.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProductoServiceImpl productoService;
    @Override
    public void guardarPedido(List<ProductoDTO> listaProductos) {
        // Aquí debes crear un nuevo objeto Pedido y establecer la lista de productos.
        Pedido nuevoPedido = new Pedido();
        System.out.println("-------------------------------------------------------------");
        System.out.println(listaProductos);
        // Crear una lista para los detalles del pedido
        List<DetallePedido> detallesPedido = new ArrayList<>();
        // Iterar a través de los productos para crear detalles del pedido
        for (ProductoDTO productoDTO : listaProductos) {
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(nuevoPedido);
            detalle.setProducto(productoService.buscarProductoPorId(productoDTO.getIdProducto()));
            detalle.setCantidad(productoDTO.getCantidad());
            detallesPedido.add(detalle);
        }
        // Establecer los detalles del pedido en el pedido
        nuevoPedido.setDetallesPedido(detallesPedido);
        nuevoPedido.setFechaPedido(LocalDateTime.now());
        nuevoPedido.setEstado("pendiente");
        // Realiza otros cálculos si es necesario, como el cálculo del precio total.
        // Guarda el pedido en la base de datos.
        pedidoRepository.save(nuevoPedido);
    }
}
