package com.example.tienda.service.impl;

import com.example.tienda.entity.Pedido;
import com.example.tienda.exception.PedidoException;
import com.example.tienda.exception.ProductoException;
import com.example.tienda.repository.PedidoRepository;
import com.example.tienda.service.PedidoService;
import com.example.tienda.util.MensajesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void guardarPedido(Pedido pedido) {
            pedido.setFechaPedido(LocalDate.now());
            System.out.println(pedido);
            pedidoRepository.save(pedido);
    }
    @Override
    public Pedido buscarPedidoPorId(Long idPedido) {
        Optional<Pedido> pedidoBuscado = pedidoRepository.findById(idPedido);
        return pedidoBuscado.orElseThrow(() -> new NoSuchElementException("Pedido no encontrado con el ID: " + idPedido));
    }

    @Override
    public void eliminarPedido(Long idPedido) {
        Pedido pedido = buscarPedidoPorId(idPedido);
        if(pedido != null){
            pedidoRepository.deleteById(pedido.getIdPedido());
            MensajesUtil.mostrarMensajeConfirmacion("Pedido eliminado con éxito.");
        }else {
            throw  new PedidoException("El pedido con ID: " + idPedido + "no existe.");
        }

    }
}
