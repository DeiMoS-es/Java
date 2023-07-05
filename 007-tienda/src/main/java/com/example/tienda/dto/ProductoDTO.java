package com.example.tienda.dto;

import com.example.tienda.entity.Producto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ProductoDTO {

    private String nombreProducto;
    private Double precioProducto;
    private Double ivaProducto;
    private String imgProducto;
    private Double cantidadProducto;

    public static ProductoDTO fromProducto (Optional<Producto> producto){
        ProductoDTO dto = new ProductoDTO();
        dto.setNombreProducto(producto.get().getNombreProducto());
        dto.setPrecioProducto(producto.get().getPrecioProducto());
        dto.setIvaProducto(producto.get().getIvaProducto());
        dto.setImgProducto(producto.get().getImgProducto());
        dto.setCantidadProducto(producto.get().getCantidadProducto());
        return dto;
    }
}
