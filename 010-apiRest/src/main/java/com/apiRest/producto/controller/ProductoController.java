package com.apiRest.producto.controller;

import com.apiRest.producto.dto.ProductoDTO;
import com.apiRest.producto.entity.Producto;
import com.apiRest.producto.repository.ProductoRepository;
import com.apiRest.producto.service.ProductoService;
import com.apiRest.producto.util.MensajeUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    @PostMapping("/guardar")
    public Producto guardarProducto(@RequestBody Producto producto){
        productoService.guardarProducto(producto);
        return producto;
    }
    @PutMapping("editar/{idProducto}")
    public ResponseEntity<?> editarProdcuto(@PathVariable("idProducto") Long idProducto, @RequestBody ProductoDTO productoDTO){
        return productoService.editarProducto(idProducto, productoDTO);
    }
    @GetMapping("/todos")
    public List<Producto> buscarTodosProductos(){
        return productoService.buscarProductos();
    }
    @GetMapping("/buscar/{idProducto}")
    public Producto buscarPorId(@PathVariable("idProducto") Long idProducto){
        return productoService.buscarProductoPorId(idProducto);
    }
    @GetMapping("buscarNombre/{nombreProducto}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable("nombreProducto") String nombreProducto){
        return productoService.buscarProductoPorNombre(nombreProducto);
    }
    @DeleteMapping("eliminar/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("idProducto") Long idProducto){
        return productoService.eliminarProducto(idProducto);
    }

    @GetMapping("buscarEnTiempoReal/{nombreProducto}")
    public ResponseEntity<?> buscarEnTiempoReal(@PathVariable("nombreProducto") String nombreProducto) {
        List<Producto> productos = productoRepository.findByNombreProductoContaining(nombreProducto);
        return ResponseEntity.ok(productos);
    }
}
