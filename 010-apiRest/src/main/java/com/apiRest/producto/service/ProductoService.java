package com.apiRest.producto.service;

import com.apiRest.producto.entity.Producto;

import java.util.List;

public interface ProductoService {

    void guardarProducto(Producto producto);
    void editarProducto(Producto producto);
    Producto buscarProductoPorId(Long idProducto);
    List<Producto> buscarProductos();
    void eliminarProducto(Long idProducto);
    Producto buscarProductoPorNombre(String nombreProducto);
}
