package com.example.tienda.service;

import com.example.tienda.dto.ProductoDTO;
import com.example.tienda.entity.Producto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductoService{
    void guardarProducto(Producto producto, MultipartFile imagen);
    void editarProducto(Long idProducto, ProductoDTO producto, MultipartFile nuevaImagen);
    Producto buscarProductoPorId(Long idProducto);
    List<Producto> buscarProductos();
    void eliminarProducto(Long idProducto);
    Producto buscarProductoPorNombre(String nombreProducto);
}
