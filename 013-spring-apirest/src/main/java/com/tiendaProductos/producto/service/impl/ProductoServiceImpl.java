package com.tiendaProductos.producto.service.impl;

import com.tiendaProductos.cloudinary.dto.Mensaje;
import com.tiendaProductos.cloudinary.entity.Imagen;
import com.tiendaProductos.cloudinary.exception.ImagenException;
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
import java.awt.*;
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
                    String nombreImagen = imagen.getOriginalFilename().split("\\.")[0];
                    Optional<Imagen> imagenOptional = imagenService.buscarImagenPorNombre(nombreImagen);
                    if (imagenOptional.isPresent()) {
                        throw new ImagenException("Ya existe una imagen con el nombre: " + nombreImagen);
                    }
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
    public void editarProducto(Long idProducto, Producto producto, MultipartFile imagen) throws IOException {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if(optionalProducto.isPresent()){
            Producto productoActualizado = optionalProducto.get();
            // Verificar y actualizar cada propiedad solo si no es nula o vacía
            if (!StringUtils.isEmpty(producto.getNombreProducto())) {
                productoActualizado.setNombreProducto(producto.getNombreProducto());
            }
            if (!StringUtils.isEmpty(producto.getDescripcionProducto())) {
                productoActualizado.setDescripcionProducto(producto.getDescripcionProducto());
            }
            if (producto.getPrecioProducto() != null) {
                productoActualizado.setPrecioProducto(producto.getPrecioProducto());
            }
            if (producto.getStockProducto() != null && producto.getStockProducto() instanceof Integer) {
                productoActualizado.setStockProducto(producto.getStockProducto());
            }
            if (!StringUtils.isEmpty(producto.getTipoProducto())) {
                productoActualizado.setTipoProducto(producto.getTipoProducto());
            }
            if(imagen == null){
                productoActualizado.setImagen(optionalProducto.get().getImagen());
            } else{
                //Si no es null buscar si existe en la bbdd, si existe poner esa, si no poner la que viene
                String nombreImagen = imagen.getOriginalFilename().split("\\.")[0];
                Optional<Imagen> imagenOptional = imagenService.buscarImagenPorNombre(nombreImagen);
                if(imagenOptional.isPresent()){
                    productoActualizado.setImagen(imagenOptional.get());
                } else{
                    Map resultadoCloudinary = cloudinaryService.upload(imagen);
                    Imagen imagenAGuardar = new Imagen((String) resultadoCloudinary.get("original_filename"),
                            (String) resultadoCloudinary.get("url"),
                            (String) resultadoCloudinary.get("public_id"));
                    imagenService.saveImagen(imagenAGuardar);
                    productoActualizado.setImagen(imagenAGuardar);
                }
            }
            // Guardar el producto actualizado en la base de datos
            productoRepository.save(productoActualizado);
            String mensajeExito = "El producto: " + producto.getNombreProducto() + " se ha actualizado correctamente";
            MensajeUtil.mensajeConfirmacion(mensajeExito);
        }else{
            String mensajeError = "El producto con ID: " + idProducto + " no se ha encontrado.";
            MensajeUtil.mensajeError(mensajeError);
            throw new ProductoException("El producto con ID: " + idProducto + " no se ha encontrado.");
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
    public ResponseEntity<?> eliminarProducto(Long idProducto) throws IOException {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if(optionalProducto.isPresent()){
            Imagen imagen = imagenService.buscarImgenById(optionalProducto.get().getImagen().getIdImagen()).get();
            Map result = cloudinaryService.delete(imagen.getIdCloudinary());
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
