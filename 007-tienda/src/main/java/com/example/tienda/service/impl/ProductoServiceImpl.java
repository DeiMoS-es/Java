package com.example.tienda.service.impl;

import com.example.tienda.entity.Producto;
import com.example.tienda.exception.ProductoException;
import com.example.tienda.repository.ProductoRepository;
import com.example.tienda.service.ProductoService;
import com.example.tienda.util.MensajesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void guardarProducto(Producto producto, MultipartFile imagen) {
        Optional<Producto> optionalProducto = (productoRepository.findByNombreProducto(producto.getNombreProducto()));
        if(optionalProducto.isEmpty()){
            if(producto.getPrecioProducto() == null || producto.getPrecioProducto().equals("")){
                throw new IllegalArgumentException("El precio es nulo o vacío");
            }
            if(producto.getIvaProducto() == null || producto.getIvaProducto().equals("")){
                producto.setIvaProducto(4.0);
            }
            if(producto.getCantidadProducto() == null || producto.getCantidadProducto().equals("")){
                throw new IllegalArgumentException("La cantidad es nula o vacía");
            }
            if (imagen != null && !imagen.isEmpty()) {
                String nombreImagen = imagen.getOriginalFilename();
                String rutaImagen = "uploads/" + nombreImagen;
                try {
                    Path directorioDestino = Paths.get("src/main/resources/static/uploads");
                    Files.createDirectories(directorioDestino);
                    Path archivoDestino = directorioDestino.resolve(nombreImagen);
                    Files.copy(imagen.getInputStream(), archivoDestino, StandardCopyOption.REPLACE_EXISTING);

                    producto.setImgProducto(rutaImagen);
                } catch (IOException e) {
                    e.printStackTrace();
                    // Manejo de errores
                }
            } else {
                producto.setImgProducto("/resources/static/imagenes/default.jpeg");
            }
            producto.setFechaAlta(LocalDate.now());
            productoRepository.save(producto);
            MensajesUtil.mostrarMensajeConfirmacion("El producto se ha guardado.");
        }else{
            throw new ProductoException("El producto ya existe con el nombre: " + producto.getNombreProducto());
        }
    }

    @Override
    public void editarProducto(Long idProducto, Producto producto) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if(optionalProducto.isPresent()){
            Producto productoActualizado = optionalProducto.get();
            if(producto.getNombreProducto().equals("") || producto.getNombreProducto().isEmpty() || producto.getNombreProducto() == null){
                productoActualizado.setNombreProducto(optionalProducto.get().getNombreProducto());//si viene vacío hay que poner el que ya existía
            }
            if(producto.getPrecioProducto().equals("") || producto.getPrecioProducto() == null){
                productoActualizado.setPrecioProducto(optionalProducto.get().getPrecioProducto());
            }
            //Todo faltaría por añadir editar la imagen, si no la sube, dejar la que estaba, si sube nueva eliminar la antigua y añadir la nueva
            else {
                productoActualizado.setNombreProducto(producto.getNombreProducto());
                productoActualizado.setPrecioProducto(producto.getPrecioProducto());
                productoActualizado.setIvaProducto(producto.getIvaProducto());
                productoRepository.save(productoActualizado);
            }

        }else{
            throw new ProductoException("El producto con ID " + idProducto + " no existe.");
        }
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
        MensajesUtil.mostrarMensajeConfirmacion("Producto eliminado con éxito");
    }

    @Override
    public Producto buscarProductoPorNombre(String nombreProducto) {
        Optional<Producto> optionalProducto = productoRepository.findByNombreProducto(nombreProducto);
        return optionalProducto.orElseThrow(() -> new NoSuchElementException("Producto no encontrado con NOMBRE: " + nombreProducto));
    }
}
