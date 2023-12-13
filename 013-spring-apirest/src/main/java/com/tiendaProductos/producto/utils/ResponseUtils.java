package com.tiendaProductos.producto.utils;

import com.tiendaProductos.producto.dto.ProductoDTO;
import com.tiendaProductos.producto.entity.Producto;

import java.util.HashMap;

public class ResponseUtils {
    public static HashMap<String, Object> construirRespuestaGuardadoExitoso(Producto producto) {
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put("producto", producto);
        respuesta.put("mensaje", "El producto: " + producto.getNombreProducto() + " se ha guardado con éxito.");
        return respuesta;
    }

    public static HashMap<String, Object> construirRespuestaEditarExitoso(Producto producto) {
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put("producto", producto);
        respuesta.put("mensaje", "El producto: " + producto.getNombreProducto() + " se ha editado con éxito.");
        return respuesta;
    }

    public static HashMap<String, Object> construirRespuestaGuardadoError(String mensajeError) {
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put("producto", null);
        respuesta.put("mensaje", mensajeError);
        return respuesta;
    }
}
