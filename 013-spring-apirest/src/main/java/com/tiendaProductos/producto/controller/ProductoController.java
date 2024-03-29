package com.tiendaProductos.producto.controller;

import com.tiendaProductos.producto.dto.ProductoDTO;
import com.tiendaProductos.producto.entity.Producto;
import com.tiendaProductos.producto.exception.ProductoException;
import com.tiendaProductos.producto.repository.ProductoRepository;
import com.tiendaProductos.producto.service.ProductoService;
import com.tiendaProductos.producto.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/listarProductos")
    public List<Producto> listarProducos(){
        return productoService.listarProductos();
    }
    @GetMapping("/buscar/{idProducto}")
    public ResponseEntity<?> buscarPorId(@PathVariable("idProducto") Long idProducto){
        return productoService.buscarProductoPorId(idProducto);
    }
    @GetMapping("/buscarNombre/{nombreProducto}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable("nombreProducto") String nombreProducto ){
        return productoService.buscaProductoPorNombre(nombreProducto);
    }
    @GetMapping("/buscarEnTiempoReal/{nombreProducto}")
    public ResponseEntity<?> buscarEnTiempoReal(@PathVariable("nombreProducto") String nombreProducto){
        List<Producto> productos = productoService.buscarEnTiempoReal(nombreProducto);
        return ResponseEntity.ok(productos);
    }
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@RequestBody Producto producto) {
        try {
            productoService.guardarProducto(producto);
            // Crear la respuesta exitosa utilizando la clase de utilidad
            HashMap<String, Object> respuestaExitosa = ResponseUtils.construirRespuestaGuardadoExitoso(producto);
            return ResponseEntity.ok(respuestaExitosa);
        } catch (ProductoException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    @PutMapping("/editar/{idProducto}")
    public ResponseEntity<?> editarProducto(@PathVariable Long idProducto, @RequestBody ProductoDTO productoDTO) {
        try {
            ResponseEntity<?> respuesta = productoService.editarProducto(idProducto, productoDTO);
            if (respuesta.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(respuesta.getBody());
            } else if (respuesta.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Puedes acceder al cuerpo de la respuesta (mensaje de error) con respuesta.getBody()
                // Ejemplo: String mensajeError = (String) respuesta.getBody();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta.getBody());
            } else {
                return respuesta;
            }
        } catch (ProductoException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<?> eliminarPRoducto(@PathVariable("idProducto") Long idProducto){
        return productoService.eliminarProducto(idProducto);
    }
}
