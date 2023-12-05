package com.tiendaProductos.producto.service.impl;

import com.tiendaProductos.cloudinary.dto.Mensaje;
import com.tiendaProductos.cloudinary.entity.Imagen;
import com.tiendaProductos.cloudinary.service.CloudinaryService;
import com.tiendaProductos.cloudinary.service.impl.ImagenServiceImpl;
import com.tiendaProductos.producto.dto.ProductoDTO;
import com.tiendaProductos.producto.entity.Producto;
import com.tiendaProductos.producto.exception.ProductoException;
import com.tiendaProductos.producto.repository.ProductoRepository;
import com.tiendaProductos.producto.service.ProductoService;
import com.tiendaProductos.producto.utils.MensajeUtil;
import com.tiendaProductos.producto.utils.ValidacionProducto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImagenServiceImpl imagenService;
    @Override
    @Transactional
    public void guardarProducto(Producto producto, MultipartFile imagen) {
        try {
            Optional<Producto> optionalProducto = productoRepository.findByNombreProducto(producto.getNombreProducto());
            if (optionalProducto.isEmpty()) {
                ValidacionProducto.validarProducto(producto);
                producto.setFechaAltaProducto(LocalDateTime.now());
                if(imagen != null && !imagen.isEmpty()){
                    Map resultadoCloudinary = cloudinaryService.upload(imagen);//Subo la imagen a cloudinary
                    Imagen imagenGuardada = new Imagen((String) resultadoCloudinary.get("original_filename"),
                                                        (String) resultadoCloudinary.get("url"),
                                                        (String) resultadoCloudinary.get("public_id"));
                    imagenService.saveImagen(imagenGuardada);
                    // Asociar la imagen al producto
                    producto.setImagen(imagenGuardada);
                }
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
        } catch (IOException e) {
            // Manejo de la excepción de carga de imagen
            System.out.println("Error al cargar la imagen: " + e.getMessage());
            throw new RuntimeException("Error al cargar la imagen.", e);
        }
    }
    @Override
    public ResponseEntity<?> editarProducto(Long idProducto, ProductoDTO productoDTO) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if(optionalProducto.isPresent()){
            Producto productoActualizado = optionalProducto.get();
            // Verificar y actualizar cada propiedad solo si no es nula o vacía
            if (!StringUtils.isEmpty(productoDTO.getNombreProducto())) {
                productoActualizado.setNombreProducto(productoDTO.getNombreProducto());
            }
            if (!StringUtils.isEmpty(productoDTO.getDescripcionProducto())) {
                productoActualizado.setDescripcionProducto(productoDTO.getDescripcionProducto());
            }
            if (productoDTO.getPrecioProducto() != null) {
                productoActualizado.setPrecioProducto(productoDTO.getPrecioProducto());
            }
            if (productoDTO.getStockProducto() != null && productoDTO.getStockProducto() instanceof Integer) {
                productoActualizado.setStockProducto(productoDTO.getStockProducto());
            }
            if (!StringUtils.isEmpty(productoDTO.getTipoProducto())) {
                productoActualizado.setTipoProducto(productoDTO.getTipoProducto());
            }
            // Guardar el producto actualizado en la base de datos
            productoRepository.save(productoActualizado);
            String mensajeExito = "El producto: " + productoDTO.getNombreProducto() + " se ha actualizado correctamente";
            MensajeUtil.mensajeConfirmacion(mensajeExito);
            return ResponseEntity.ok(ProductoDTO.fromProducto(productoActualizado));
        }else{
            String mensajeError = "El producto con ID: " + idProducto + " no se ha encontrado.";
            MensajeUtil.mensajeError(mensajeError);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
    @Override
    public ResponseEntity<?> buscarProductoPorId(Long idProducto) {
        Optional<Producto>  optionalProducto = productoRepository.findById(idProducto);
        if(optionalProducto.isPresent()){
            return ResponseEntity.ok(optionalProducto);
        }else {
            String mensajeError = "El producto con nombre: " + idProducto + " no se ha encontrado.";
            MensajeUtil.mensajeError(mensajeError);
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
    @Override
    public ResponseEntity<?> buscaProductoPorNombre(String nombreProducto) {
        Optional<Producto> optionalProducto = productoRepository.findByNombreProducto(nombreProducto);
        if(optionalProducto.isPresent()){
            return ResponseEntity.ok(optionalProducto);
        }else {
            String mensajeError = "El producto con nombre: " + nombreProducto + " no se ha encontrado.";
            MensajeUtil.mensajeError(mensajeError);
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @Override
    public ResponseEntity<?> eliminarProducto(Long idProducto) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if(optionalProducto.isPresent()){
            productoRepository.deleteById(idProducto);
            String mensajeOk = "El producto: " + optionalProducto.get().getNombreProducto() + " se ha eliminado.";
            MensajeUtil.mensajeConfirmacion(mensajeOk);
            return ResponseEntity.status(HttpStatus.OK).body("{\"mensaje\": \"" + mensajeOk + "\"}");
        }else{
            String mensajeError = "El producto con ID: " + idProducto + " no se ha encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\": \"" + mensajeError + "\"}");
        }
    }

    @Override
    public List<Producto> buscarEnTiempoReal(String nombreProducto) {
        return productoRepository.findByNombreProductoContaining(nombreProducto);
    }
}
