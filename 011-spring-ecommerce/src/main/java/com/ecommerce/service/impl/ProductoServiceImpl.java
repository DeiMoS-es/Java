package com.ecommerce.service.impl;

import com.ecommerce.model.Producto;
import com.ecommerce.repository.ProductoRepository;
import com.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    @Override
    public Optional<Producto> getProducto(Integer idProducto) {
        return productoRepository.findById(idProducto);
    }
    @Override
    public void updateProducto(Producto producto) {
        productoRepository.save(producto);//save se comporta: si llega un null, lo crea y si mandamos un id que existe lo actualiza
    }

    @Override
    public void deleteProducto(Integer idProducto) {
        productoRepository.deleteById(idProducto);
    }
}
