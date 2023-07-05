package com.example.tienda.controller;

import com.example.tienda.entity.Producto;
import com.example.tienda.repository.ProductoRepository;
import com.example.tienda.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("buscar/{nombreProducto}")
    public Producto buscarProducto (@PathVariable("nombreProducto") String nombreProducto){
       return productoService.buscarProductoPorNombre(nombreProducto);
    }

    @PostMapping("/guardar")
    public void guardarProducto(@RequestBody Producto producto, @RequestParam(value = "imagen", required = false) MultipartFile imagen)throws Exception{
        Producto productoGuardar = producto;
        productoService.guardarProducto(productoGuardar, imagen);
        System.out.println("Producto guardado con éxito");
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarProducto(@PathVariable("id") Long idProducto){
        productoService.eliminarProducto(idProducto);
    }
}
