package com.tiendaProductos.producto.service;

import com.tiendaProductos.producto.dto.ProductoDTO;
import com.tiendaProductos.producto.entity.Producto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ProductoService {

    void guardarProducto(Producto producto);
    ResponseEntity<?> editarProducto(Long idProducto, ProductoDTO productoDTO);
    ResponseEntity<?> buscarProductoPorId(Long idProducto);
    List<Producto> listarProductos();
    ResponseEntity<?> buscaProductoPorNombre(String nombreProducto);
    ResponseEntity<?> eliminarProducto(Long idProducto);
}
