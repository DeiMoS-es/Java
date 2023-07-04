package com.example.tienda.service;

import com.example.tienda.entity.Producto;

import java.util.List;

public interface ProductoService{
    Producto guardarProducto(Producto producto);
    Producto editarProducto(Producto producto);
    Producto buscarProductoPorId(Long idProducto);
    List<Producto> buscarProductos();
    void eliminarProducto(Long idProducto);

    Producto buscarProductoPorNombre(String nombreProducto);
}
