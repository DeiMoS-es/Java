package com.tiendaProductos.pedido.service.impl;

import com.tiendaProductos.pedido.entity.DetallePedido;
import com.tiendaProductos.pedido.entity.Pedido;
import com.tiendaProductos.pedido.exception.PedidoException;
import com.tiendaProductos.pedido.repository.PedidoRepository;
import com.tiendaProductos.pedido.service.PedidoService;
import com.tiendaProductos.producto.dto.ProductoDTO;
import com.tiendaProductos.producto.entity.Producto;
import com.tiendaProductos.producto.repository.ProductoRepository;
import com.tiendaProductos.producto.service.ProductoService;
import com.tiendaProductos.producto.utils.MensajeUtil;
import com.tiendaProductos.user.entity.User;
import com.tiendaProductos.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    //TODO implementar métodos
    @Override
    public void guardarPedido(Long idUsuario ,List<ProductoDTO> listaProductos) {
        if(!listaProductos.isEmpty()){
            Pedido nuevoPedido = new Pedido();
            User usuario = userRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idUsuario));
            List<DetallePedido> detallesPedido = new ArrayList<>();
            List<Producto> listaProductosSave = new ArrayList<>();//Lista de productos para la relación ManyToMany de Pedido
            Double precioTotalPedido = 0.0;
            for(ProductoDTO productoDTO : listaProductos){
                DetallePedido detalle = new DetallePedido();
                Producto producto = productoService.buscarProductoPorId(productoDTO.getIdProducto());
                detalle.setPedido(nuevoPedido);
                detalle.setProducto(producto);
                detalle.setCantidad(productoDTO.getCantidad());
                detallesPedido.add(detalle);
                listaProductosSave.add(producto);
                precioTotalPedido += calcularPrecioTotalPedido(productoDTO.getCantidad(), productoDTO.getPrecioProducto());
                actualizarStockProducto(producto, productoDTO.getCantidad());
            }
            // Añadimos los detalles del pedido en el pedido, para dar de alta el pedido
            nuevoPedido.setDetallesPedido(detallesPedido);
            nuevoPedido.setProductos(listaProductosSave);
            nuevoPedido.setFechaPedido(LocalDateTime.now());
            nuevoPedido.setPrecioTotal(precioTotalPedido);
            nuevoPedido.setEstadoPedido("creado");
            nuevoPedido.setUsuario(usuario);
            pedidoRepository.save(nuevoPedido);
            MensajeUtil.mensajeConfirmacion("El pedido se ha guardado correctamente");
        }
    }
    @Override
    public Pedido buscarPedidoPorId(Long idPedido) {
        return pedidoRepository.findById(idPedido).orElseThrow(()-> new NoSuchElementException("Pedido con ID: " + idPedido + " no encontrado"));
    }
    @Override
    public boolean eliminarPedido(Long idPedido) {
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        if(pedido.isPresent()){
            pedidoRepository.deleteById(idPedido);
            return true;
        }else{
            return false;
        }
    }
    private static double calcularPrecioTotalPedido(int cantidad, double precioProducto){
        return cantidad * precioProducto;
    }
    private static void actualizarStockProducto(Producto producto, int cantidad){
        if(producto.getStockProducto() - cantidad >= 0){
            producto.setStockProducto(producto.getStockProducto() - cantidad);
        } else{
            throw new PedidoException("No hay stock del producto: " + producto.getNombreProducto() + " inténtelo de nuevo más tarde.");
        }

    }
}
