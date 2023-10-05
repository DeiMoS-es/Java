package com.apiRest.pedido.dto;

import com.apiRest.pedido.entity.Pedido;
import com.apiRest.producto.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    //quiero ver la fecha del pedido, el estado, los productos y el //TODO usuario que lo ha hecho
    private LocalDateTime fechaPedido;
    private String estado;
    private List<Producto> productos;

    public static PedidoDTO fromPedido(Pedido pedido){
        PedidoDTO dto = new PedidoDTO();
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setEstado(pedido.getEstado());
        //dto.setProductos(pedido.getProductos());
        return dto;
    }
}
