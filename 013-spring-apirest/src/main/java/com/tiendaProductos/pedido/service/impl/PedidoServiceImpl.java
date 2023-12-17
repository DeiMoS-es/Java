package com.tiendaProductos.pedido.service.impl;

import com.tiendaProductos.pedido.entity.DetallePedido;
import com.tiendaProductos.pedido.entity.Pedido;
import com.tiendaProductos.pedido.repository.PedidoRepository;
import com.tiendaProductos.pedido.service.PedidoService;
import com.tiendaProductos.producto.dto.ProductoDTO;
import com.tiendaProductos.producto.entity.Producto;
import com.tiendaProductos.producto.repository.ProductoRepository;
import com.tiendaProductos.producto.service.ProductoService;
import com.tiendaProductos.producto.utils.MensajeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    //TODO implementar métodos
    @Override
    public void guardarPedido(List<ProductoDTO> listaProductos) {
        if(!listaProductos.isEmpty()){
            Pedido nuevoPedido = new Pedido();
            List<DetallePedido> detallesPedido = new ArrayList<>();
            List<Producto> listaProductosSave = new ArrayList<>();//Lista de productos para la relación ManyToMany de Pedido
            for(ProductoDTO productoDTO : listaProductos){
                DetallePedido detalle = new DetallePedido();
                detalle.setPedido(nuevoPedido);
                detalle.setProducto(productoService.buscarProductoPorId(productoDTO.getIdProducto()));
                detalle.setCantidad(productoDTO.getCantidad());
                detallesPedido.add(detalle);
                listaProductosSave.add(productoService.buscarProductoPorId(productoDTO.getIdProducto()));
            }
            // Añadimos los detalles del pedido en el pedido, para dar de alta el pedido
            nuevoPedido.setDetallesPedido(detallesPedido);
            nuevoPedido.setProductos(listaProductosSave);
            nuevoPedido.setFechaPedido(LocalDateTime.now());
            nuevoPedido.setEstadoPedido("creado");
            //TODO faltaría por calcular el precio total del pedido, y modificar el sotck de los productos restando la cantidad pedida
            //calcularPrecioTotalPedido(nuevoPedido);
            pedidoRepository.save(nuevoPedido);
            MensajeUtil.mensajeConfirmacion("El pedido se ha guardado correctamente");
        }
    }

   // @Override
    //public Pedido buscarPedidoPorId(Long idPedido) {
    //    return null;
   // }

   // @Override
   // public ResponseEntity<?> eliminarPedido(Long idPedido) {
     //   return null;
   // }

    private static double calcularPrecioTotalPedido(Pedido pedido){
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+-+-");
        System.out.println(pedido);
        //for (Producto producto : pedido.getProductos()){
          //  System.out.println(producto);
        //}
        return 0.0;
    }
}
