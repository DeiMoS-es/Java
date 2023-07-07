package com.example.tienda.controller;

import com.example.tienda.dto.ProductoDTO;
import com.example.tienda.entity.Producto;
import com.example.tienda.exception.ProductoException;
import com.example.tienda.repository.ProductoRepository;
import com.example.tienda.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    @GetMapping("/listar")
    public List<Producto> listarProductos (){
        return productoRepository.findAll();
    }

    @GetMapping("buscar/nombre/{nombreProducto}")
    public ResponseEntity<ProductoDTO> buscarProductoNombre (@PathVariable("nombreProducto") String nombreProducto){
        Optional<Producto> productoBuscado = productoRepository.findByNombreProducto(nombreProducto);
        if(productoBuscado.isPresent()){
            ProductoDTO productoDTO = ProductoDTO.fromProducto(productoBuscado);
            return ResponseEntity.ok(productoDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable("id") Long idProducto){
        Optional<Producto> productoBuscado = productoRepository.findById(idProducto);
        if(productoBuscado.isPresent()){
            return ResponseEntity.ok(productoBuscado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void guardarProducto(@ModelAttribute Producto producto, @RequestParam(value = "imagen", required = false) MultipartFile imagen)throws Exception{
        Producto productoGuardar = producto;
        productoService.guardarProducto(productoGuardar, imagen);
        System.out.println("Producto guardado con éxito");
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarProducto(@PathVariable("id") Long idProducto){
        productoService.eliminarProducto(idProducto);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<String> editarProducto(@PathVariable("id") Long idProducto,
                                                 @RequestParam(value = "imagen", required = false) MultipartFile imagen,
                                                 @RequestBody ProductoDTO productoDTO) {
        try {
            // Lógica para editar el producto
            productoService.editarProducto(idProducto, productoDTO, imagen);

            return ResponseEntity.ok("El producto se ha editado correctamente");
        } catch (ProductoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
