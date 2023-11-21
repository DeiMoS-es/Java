package com.tiendaProductos.producto.service.impl;

import com.tiendaProductos.producto.dto.ProductoDTO;
import com.tiendaProductos.producto.entity.Producto;
import com.tiendaProductos.producto.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Override
    public void guardarProducto(Producto producto) {

    }
    @Override
    public ResponseEntity<?> editarProducto(Long idProducto, ProductoDTO productoDTO) {
        return null;
    }
    @Override
    public Producto buscarProductoPorId(Long idProducto) {
        return null;
    }
    @Override
    public List<Producto> buscarProductos() {
        return null;
    }
    @Override
    public ResponseEntity<?> buscaProductoPorNombre(String nombreProducto) {
        return null;
    }
}
