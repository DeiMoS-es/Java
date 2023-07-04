package com.example.tienda.service.impl;

import com.example.tienda.entity.Producto;
import com.example.tienda.repository.ProductoRepository;
import com.example.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto guardarProducto(Producto producto) {

        return null;
    }

    @Override
    public Producto editarProducto(Producto producto) {
        return null;
    }

    @Override
    public Producto buscarProductoPorId(Long idProducto) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        return optionalProducto.orElseThrow(() -> new NoSuchElementException("Producto no encontrado con ID: " + idProducto));
    }

    @Override
    public List<Producto> buscarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        Producto producto = buscarProductoPorId(idProducto);
        productoRepository.deleteById(producto.getIdProducto());
    }

    @Override
    public Producto buscarProductoPorNombre(String nombreProducto) {
        //Igual sería más conveniente cambiar a que devuelva un Optional
        Optional<Producto> optionalProducto = productoRepository.findByNombreProducto(nombreProducto);
        return optionalProducto.orElseThrow(() -> new NoSuchElementException("Producto no encontrado con NOMBRE: " + nombreProducto));
    }
}
