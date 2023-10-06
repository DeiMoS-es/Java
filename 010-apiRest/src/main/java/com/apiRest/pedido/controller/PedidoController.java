package com.apiRest.pedido.controller;

import com.apiRest.pedido.entity.Pedido;
import com.apiRest.pedido.repository.PedidoRepository;
import com.apiRest.pedido.service.PedidoService;
import com.apiRest.producto.dto.ProductoDTO;
import com.apiRest.producto.entity.Producto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;

    @PostMapping("/guardar")
    public ResponseEntity<Pedido> guardarPedido(@RequestBody List<ProductoDTO> listaProductos){
        pedidoService.guardarPedido(listaProductos);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
