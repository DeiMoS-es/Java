package com.example.tienda.service.impl;

import com.example.tienda.dto.ProductoDTO;
import com.example.tienda.entity.Producto;
import com.example.tienda.exception.ProductoException;
import com.example.tienda.repository.ProductoRepository;
import com.example.tienda.service.ImagenService;
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
    @Autowired
    private ImagenService imagenService;

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
               String rutaImagen = imagenService.guardarImagen(imagen);
               System.out.println("-------------------------------------------------------------------------------------");
                System.out.println(rutaImagen);
               producto.setImgProducto(rutaImagen);
            } else {
                producto.setImgProducto("/imagenes/default.jpeg");
            }
            producto.setFechaAlta(LocalDate.now());

            productoRepository.save(producto);
            MensajesUtil.mostrarMensajeConfirmacion("El producto se ha guardado.");
        }else{
            throw new ProductoException("El producto ya existe con el nombre: " + producto.getNombreProducto());
        }
    }
    private void eliminarImagen(String nombreImagen){
        String rutaImagen = "src/main/resources/static/uploads" + nombreImagen;
        try{
            Path archivo = Paths.get(rutaImagen);
            if(Files.exists(archivo)){
                Files.delete(archivo);
                System.out.println("Imagen eliminada: " + nombreImagen);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }
    private String guardarImagen(MultipartFile imagen){
        String nombreImagen = imagen.getOriginalFilename();
        String rutaImagen = "src/main/resources/static/uploads/" + nombreImagen;

        try {
            // Obtener el path absoluto del archivo
            Path rutaAbsoluta = Paths.get(rutaImagen).toAbsolutePath();

            // Guardar la imagen en el path absoluto
            Files.copy(imagen.getInputStream(), rutaAbsoluta, StandardCopyOption.REPLACE_EXISTING);

            return nombreImagen;
        } catch (IOException e) {
            // Manejo de errores
            e.printStackTrace();
            System.out.println("Error al guardar la imagen");
        }
        return nombreImagen;
    };
    @Override
    public void editarProducto(Long idProducto, ProductoDTO productoDTO, MultipartFile nuevaImagen) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        System.out.println(optionalProducto.get());
        if(optionalProducto.isPresent()){
            Producto productoActualizado = optionalProducto.get();
            if(productoDTO.getNombreProducto().equals("") || productoDTO.getNombreProducto().isEmpty() || productoDTO.getNombreProducto() == null){
                productoActualizado.setNombreProducto(optionalProducto.get().getNombreProducto());//si viene vacío hay que poner el que ya existía
            }
            if(productoDTO.getPrecioProducto().equals("") || productoDTO.getPrecioProducto() == null){
                productoActualizado.setPrecioProducto(optionalProducto.get().getPrecioProducto());
            }
            if(nuevaImagen != null && !nuevaImagen.isEmpty()){
                System.out.println("-------------------------------------------------------------------------------");
                eliminarImagen(optionalProducto.get().getImgProducto());
                String nombreImagen = guardarImagen(nuevaImagen);
                System.out.println(nombreImagen);
                productoActualizado.setImgProducto(nombreImagen);
            }
            //Todo faltaría por añadir editar la imagen, si no la sube, dejar la que estaba, si sube nueva eliminar la antigua y añadir la nueva
            else {
                productoActualizado.setNombreProducto(productoDTO.getNombreProducto());
                productoActualizado.setPrecioProducto(productoDTO.getPrecioProducto());
                productoActualizado.setIvaProducto(productoDTO.getIvaProducto());
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
