package com.ecommerce.controller;

import com.ecommerce.model.Producto;
import com.ecommerce.model.Usuario;
import com.ecommerce.service.ProductoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    private final ProductoService productoService;

    @GetMapping("")
    public String show(Model model){
        //Este método recibe un parámetro (nombre/varibale) en la que se val a "almacenar" los datos, en este caso una lista dr proudctos
        //Y el segundo parámetro es la información, la variable o el objeto que tiene la información
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto){
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario u = new Usuario(1, "","","","","","","");
        producto.setUsuario(u);
        productoService.saveProducto(producto);
        return "redirect:/productos";
    }
}
