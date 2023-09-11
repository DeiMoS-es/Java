package com.apiRest.producto.service;

import com.apiRest.producto.dto.ProductoDTO;
import com.apiRest.producto.entity.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {
    void guardarProducto(Producto producto);
    ResponseEntity<?> editarProducto(Long idProducto, ProductoDTO producto);
    Producto buscarProductoPorId(Long idProducto);
    List<Producto> buscarProductos();
    ResponseEntity<?> eliminarProducto(Long idProducto);
    ResponseEntity<?> buscarProductoPorNombre(String nombreProducto);
}
