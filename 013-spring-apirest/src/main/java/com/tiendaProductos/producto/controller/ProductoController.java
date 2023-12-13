package com.tiendaProductos.producto.controller;

import com.tiendaProductos.cloudinary.dto.Mensaje;
import com.tiendaProductos.cloudinary.entity.Imagen;
import com.tiendaProductos.cloudinary.exception.ImagenException;
import com.tiendaProductos.cloudinary.service.CloudinaryService;
import com.tiendaProductos.cloudinary.service.ImagenService;
import com.tiendaProductos.producto.dto.ProductoDTO;
import com.tiendaProductos.producto.entity.Producto;
import com.tiendaProductos.producto.exception.ProductoException;
import com.tiendaProductos.producto.repository.ProductoRepository;
import com.tiendaProductos.producto.service.ProductoService;
import com.tiendaProductos.producto.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
@CrossOrigin()
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/listarProductos")
    public List<Producto> listarProducos(){
        return productoService.listarProductos();
    }
    @GetMapping("/buscar/{idProducto}")
    public ResponseEntity<?> buscarPorId(@PathVariable("idProducto") Long idProducto){
        Producto producto = productoService.buscarProductoPorId(idProducto);
        if(producto != null){
            return ResponseEntity.ok(producto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/buscarNombre/{nombreProducto}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable("nombreProducto") String nombreProducto ){
        Producto producto = productoService.buscaProductoPorNombre(nombreProducto);
        if(producto != null){
            return ResponseEntity.ok(producto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/buscarEnTiempoReal/{nombreProducto}")
    public ResponseEntity<?> buscarEnTiempoReal(@PathVariable("nombreProducto") String nombreProducto){
        List<Producto> productos = productoService.buscarEnTiempoReal(nombreProducto);
        return ResponseEntity.ok(productos);
    }
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@RequestParam("multipartFile") MultipartFile multipartFile, @ModelAttribute Producto producto) throws IOException {
        try {
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if(bi==null){
                return new ResponseEntity(new Mensaje("Imagen no valida."), HttpStatus.BAD_REQUEST);
            }
            productoService.guardarProducto(producto, multipartFile);
            // Crear la respuesta exitosa utilizando la clase de utilidad
            HashMap<String, Object> respuestaExitosa = ResponseUtils.construirRespuestaGuardadoExitoso(producto);
            return ResponseEntity.ok(respuestaExitosa);
        } catch (ImagenException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (ProductoException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    @PutMapping("/editar/{idProducto}")
    public ResponseEntity<?> editarProducto(@RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile, @PathVariable Long idProducto, @ModelAttribute Producto producto) {
        try{
            productoService.editarProducto(idProducto, producto, multipartFile);
            // Crear la respuesta exitosa utilizando la clase de utilidad
            HashMap<String, Object> respuestaExitosa = ResponseUtils.construirRespuestaEditarExitoso(producto);
            return ResponseEntity.ok(respuestaExitosa);
        }
        catch (ImagenException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        catch(ProductoException | IOException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<?> eliminarPRoducto(@PathVariable("idProducto") Long idProducto) throws IOException {
        return productoService.eliminarProducto(idProducto);
    }
}
