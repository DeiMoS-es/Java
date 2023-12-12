package com.tiendaProductos.producto.service;

import com.tiendaProductos.producto.dto.ProductoDTO;
import com.tiendaProductos.producto.entity.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductoService {
    void guardarProducto(Producto producto, MultipartFile multipartFile);
    ResponseEntity<?> editarProducto(Long idProducto, Producto producto, MultipartFile multipartFile) throws IOException;
    ResponseEntity<?> buscarProductoPorId(Long idProducto);
    List<Producto> listarProductos();
    ResponseEntity<?> buscaProductoPorNombre(String nombreProducto);
    ResponseEntity<?> eliminarProducto(Long idProducto) throws IOException;
    List<Producto> buscarEnTiempoReal(String nombreProducto);
}
