package com.tiendaProductos.producto.service.impl;

import com.tiendaProductos.producto.dto.ProductoDTO;
import com.tiendaProductos.producto.entity.Producto;
import com.tiendaProductos.producto.exception.ProductoException;
import com.tiendaProductos.producto.repository.ProductoRepository;
import com.tiendaProductos.producto.service.ProductoService;
import com.tiendaProductos.producto.utils.MensajeUtil;
import com.tiendaProductos.producto.utils.ValidacionProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public void guardarProducto(Producto producto) {
        try {
            Optional<Producto> optionalProducto = productoRepository.findByNombreProducto(producto.getNombreProducto());
            if (optionalProducto.isEmpty()) {
                ValidacionProducto.validarProducto(producto);
                // Lógica de guardado si la validación es exitosa
                producto.setFechaAltaProducto(LocalDateTime.now());
                productoRepository.save(producto);
                MensajeUtil.mensajeConfirmacion("El producto se ha guardado.");
            } else {
                throw new ProductoException("El producto con el nombre: " + producto.getNombreProducto() + " ya existe.");
            }
        } catch (ProductoException e) {
            // Manejo de la excepción de validación
            System.out.println("Error de validación: " + e.getMessage());
            throw e;// Relanzar la excepción después de imprimir el mensaje
            //Es decir, se está "lanzando" la misma excepción que se ha capturado, permitiendo que esa excepción se propague hacia arriba en la cadena de llamadas a métodos.
        }
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
