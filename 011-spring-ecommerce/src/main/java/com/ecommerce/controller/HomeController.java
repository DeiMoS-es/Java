
package com.ecommerce.controller;

import com.ecommerce.model.DetallePedido;
import com.ecommerce.model.Pedido;
import com.ecommerce.model.Producto;
import com.ecommerce.service.ProductoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private final ProductoService productoService;
    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    //Variable para almacenar los detalles del pedido
    List<DetallePedido> detallesPedido = new ArrayList<DetallePedido>();
    //Almacenamos los datos del pedido
    Pedido pedido = new Pedido();
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

    @PostMapping("/cart")
    public String addCart(@RequestParam("idProducto") Integer idProducto, @RequestParam("cantidad") Integer cantidad){
        DetallePedido detallePedido = new DetallePedido();
        Producto producto = new Producto();
        double sumaTotal = 0;
        Optional<Producto> optionalProducto = productoService.getProducto(idProducto);
        producto = optionalProducto.get();
        LOGGER.info("El producto añadido: {}", producto);
        LOGGER.info("Cantidad: {}", cantidad);
        return "usuario/carrito";
    }
}
