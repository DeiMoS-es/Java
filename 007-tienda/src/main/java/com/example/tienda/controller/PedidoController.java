package com.example.tienda.controller;

import com.example.tienda.entity.Pedido;
import com.example.tienda.repository.PedidoRepository;
import com.example.tienda.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping("/buscar/{id}")
    public Pedido buscarPedidoId(@PathVariable("idPedido") Long idPedido){
        return pedidoService.buscarPedidoPorId(idPedido);
    }
    @PostMapping("/guardar")
    public ResponseEntity<String> guardarPedido(@RequestBody Pedido pedido) {
        //pedido.setUsuario();
        pedidoService.guardarPedido(pedido);
        return ResponseEntity.ok("Pedido guardado correctamente");
    }
}
