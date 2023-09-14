
package com.ecommerce.controller;

import com.ecommerce.model.Producto;
import com.ecommerce.service.ProductoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private final ProductoService productoService;
    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    @GetMapping
    public String home(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "usuario/home";
    }

    @GetMapping("productohome/{idProducto}")
    public String productoHome(@PathVariable("idProducto") Integer idProducto, Model model){
        LOGGER.info("Id producto enviado como parámetro {}", idProducto);
        Producto producto = productoService.getProducto(idProducto).get();
        //Se podría crear un optional de producto
        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }
}
