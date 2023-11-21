package com.tiendaProductos.producto.utils;

import com.tiendaProductos.producto.entity.Producto;
import com.tiendaProductos.producto.exception.ProductoException;

public class ValidacionProducto {
    public static void validarProducto(Producto producto) {
        validarNombre(producto.getNombreProducto());
        validarDescripcion(producto.getDescripcionProducto());
        validarPrecio(producto.getPrecioProducto());
        validarStock(producto.getStockProducto());
        validarTipo(producto.getTipoProducto());
    }
    private static void validarNombre(String nombreProducto) {
        if (nombreProducto == null || nombreProducto.isEmpty()) {
            throw new ProductoException("El nombre del producto es nulo o vacío");
        }
    }
    private static void validarDescripcion(String descripcionProducto) {
        if (descripcionProducto == null || descripcionProducto.isEmpty()) {
            throw new ProductoException("La descripción del producto es nula o vacía");
        }
    }
    private static void validarPrecio(Double precioProducto) {
        if (precioProducto == null || Double.isNaN(precioProducto)) {
            throw new ProductoException("El precio no tiene el formato correcto");
        }
    }
    private static void validarStock(Integer stockProducto) {
        if (stockProducto == null || !(stockProducto instanceof Integer)) {
            throw new ProductoException("El stock no es un valor numérico válido");
        }
    }
    private static void validarTipo(String tipoProducto) {
        if (tipoProducto == null || tipoProducto.isEmpty() || !tipoProducto.matches("[a-zA-Z]+")) {
            throw new ProductoException("El tipo del producto no es válido");
        }
    }
}
