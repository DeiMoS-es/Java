package com.ecommerce.service;

import com.ecommerce.model.Producto;

import java.util.Optional;

/**
 * Defino todos los métodos para el crud
 */
public interface ProductoService {
    public Producto saveProducto(Producto producto);
    public Optional<Producto> getProducto(Integer idProducto);//Optional nos da la opción de validar si el objeto existe o no
    public void updateProducto(Producto producto);
    public void deleteProducto(Integer idProducto);
}
