package com.tiendaProductos.pedido.controller;

import com.tiendaProductos.pedido.entity.Pedido;
import com.tiendaProductos.pedido.repository.PedidoRepository;
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
    @PostMapping("/guardarPedido/{idUsuario}")
    public ResponseEntity<Pedido> guardarPedido(@PathVariable("idUsuario") Long idUsuario ,@RequestBody List<ProductoDTO> listaProductos){
        pedidoService.guardarPedido(idUsuario ,listaProductos);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/buscarPedido/{idPedido}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable("idPedido") Long idPedido){
        Pedido pedido = pedidoService.buscarPedidoPorId(idPedido);
        if(pedido != null){
            return ResponseEntity.ok(pedido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarPedido/{idPedido}")
    public ResponseEntity<?> eliminarPedido (@PathVariable("idPedido") Long idPedido){
       if(pedidoService.eliminarPedido(idPedido)){
           return new ResponseEntity<>("Pedido eliminado exitosamente", HttpStatus.OK);
       }else{
           return new ResponseEntity<>("No se ha podido eliminar el pedido", HttpStatus.NOT_FOUND);
       }
    }
}
