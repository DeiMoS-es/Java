package com.apiRest.producto.service.impl;

import com.apiRest.producto.dto.ProductoDTO;
import com.apiRest.producto.entity.Producto;
import com.apiRest.producto.exception.ProductoException;
import com.apiRest.producto.repository.ProductoRepository;
import com.apiRest.producto.service.ProductoService;
import com.apiRest.producto.util.MensajeUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public void guardarProducto(Producto producto) {
        Optional<Producto> optionalProducto = productoRepository.findByNombreProducto(producto.getNombreProducto());
        if(optionalProducto.isEmpty()){
            if(producto.getPrecioProducto() == null || producto.getPrecioProducto().equals("")){
                throw new IllegalArgumentException("El precio es nulo o vacío");
            } if(producto.getIvaProducto() == null || producto.getIvaProducto().equals("")){
                producto.setIvaProducto(4.0);
            }
            producto.setFechaAlta(LocalDateTime.now());
            productoRepository.save(producto);
            MensajeUtil.mensajeConfirmacion("El producto se ha guardado.");
        } else{
            throw new ProductoException("El producto con el nombre: " + producto.getNombreProducto() + " ya existe");
        }
    }

    @Override
    public ResponseEntity<?> editarProducto(Long idProducto, ProductoDTO productoDTO) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        System.out.println(optionalProducto);
        if(optionalProducto.isPresent()){
            Producto productoActualizado = optionalProducto.get();
            if(productoDTO.getNombreProducto().equals("") || productoDTO.getNombreProducto().isEmpty() || productoDTO.getNombreProducto() == null){
                //Si no viene nombre ponemos el que ya estaba
                productoActualizado.setNombreProducto(optionalProducto.get().getNombreProducto());
                productoActualizado.setPrecioProducto(productoDTO.getPrecioProducto());
                productoActualizado.setPrecioProducto(productoDTO.getPrecioProducto());
                productoRepository.save(productoActualizado);
                return ResponseEntity.ok(ProductoDTO.fromProducto(productoActualizado));
            }//TODO faltaría por añadir el resto de validaciones, para precio e iva
            else {
                productoActualizado.setNombreProducto(productoDTO.getNombreProducto());
                productoActualizado.setPrecioProducto(productoDTO.getPrecioProducto());
                productoActualizado.setPrecioProducto(productoDTO.getPrecioProducto());
                productoRepository.save(productoActualizado);
                return ResponseEntity.ok(ProductoDTO.fromProducto(productoActualizado));
            }
        }
        String mensajeError = "El producto con ID: " + idProducto + " no se ha encontrado.";
        MensajeUtil.mensajeError(mensajeError);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
    }

    @Override
    public Producto buscarProductoPorId(Long idProducto) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        return optionalProducto.orElseThrow(() -> new NoSuchElementException("Producto con ID: " + idProducto + " no encontrado."));
    }

    @Override
    public List<Producto> buscarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public ResponseEntity<?> eliminarProducto(Long idProducto) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if(optionalProducto.isPresent()){
            productoRepository.deleteById(idProducto);
            String mensajeOk = "El producto con ID: " + idProducto + " se ha eliminado.";
            MensajeUtil.mensajeConfirmacion(mensajeOk);
            return ResponseEntity.status(HttpStatus.OK).body(mensajeOk);
        }else {
            String mensajeError = "El producto con ID: " + idProducto + " no se ha encontrado.";
            MensajeUtil.mensajeError(mensajeError);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @Override
    public ResponseEntity<?> buscarProductoPorNombre(String nombreProducto) {
        Optional<Producto> optionalProducto = productoRepository.findByNombreProducto(nombreProducto);
        if (optionalProducto.isPresent()){
            return ResponseEntity.ok(optionalProducto.get());
        }else {
            String mensajeError = "El producto con nombre: " + nombreProducto + " no se ha encontrado.";
            MensajeUtil.mensajeError(mensajeError);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
}
