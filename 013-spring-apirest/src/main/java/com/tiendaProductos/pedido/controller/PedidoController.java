package com.tiendaProductos.pedido.controller;

import com.tiendaProductos.pedido.entity.Pedido;
import com.tiendaProductos.pedido.service.PedidoService;
import com.tiendaProductos.producto.dto.ProductoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("/guardarPedido")
    public ResponseEntity<Pedido> guardarPedido(@RequestBody List<ProductoDTO> listaProductos){
        pedidoService.guardarPedido(listaProductos);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
