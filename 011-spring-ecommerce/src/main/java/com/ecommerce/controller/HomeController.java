
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
    public String addCart(@RequestParam("idProducto") Integer idProducto, @RequestParam("cantidad") Integer cantidad, Model model){
        DetallePedido detallePedido = new DetallePedido();
        Producto producto = new Producto();
        double sumaTotal = 0;
        Optional<Producto> optionalProducto = productoService.getProducto(idProducto);
        producto = optionalProducto.get();
        detallePedido.setCantidad(cantidad);
        detallePedido.setPrecio(producto.getPrecio());
        detallePedido.setTotal(producto.getPrecio()*cantidad);
        detallePedido.setNombre(producto.getNombre());
        detallePedido.setProducto(producto);
        //añadimos el producto a la lista
        detallesPedido.add(detallePedido);

        sumaTotal = detallesPedido.stream().mapToDouble(dt -> dt.getTotal()).sum();
        pedido.setTotal(sumaTotal);//Añadimos al pedido el total de la suma
        LOGGER.info("Detalles de pedido: {}", detallesPedido);
        model.addAttribute("cart", detallesPedido);
        model.addAttribute("pedido", pedido);//Pasamos solo el total
        return "usuario/carrito";
    }
}
