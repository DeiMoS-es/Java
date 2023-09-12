package com.apiRest;

import com.apiRest.producto.entity.Producto;
import com.apiRest.producto.service.ProductoService;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestServicioProducto {

    @Autowired
    private ProductoService productoService;
    @Test
    public void buscarTodosProductos(){
        // Llama al método buscarProductos del servicio
        List<Producto> productos = productoService.buscarProductos();
        // Realiza aserciones para verificar que la lista de productos no sea nula y contenga elementos
        Assert.assertNotNull(productos);
        Assert.assertFalse(productos.isEmpty());
    }
    @Test
    public void testGuardarProducto() {
        // Crea un objeto Producto para guardar
        Producto producto = new Producto();
        producto.setNombreProducto("");
        producto.setPrecioProducto(20.0);

        System.out.println(producto);
        // Llama al método guardarProducto del servicio
        productoService.guardarProducto(producto);

        // Realiza aserciones para verificar que el producto se ha guardado correctamente
        // Puedes verificar, por ejemplo, si el producto tiene un ID asignado después de guardar
        Assert.assertNotNull(producto.getIdProducto());
        Assert.assertNotNull(producto.getFechaAlta());
        Assert.assertNotNull(producto.getIvaProducto());
    }

}
