package com.apiRest.producto.dto;

import com.apiRest.producto.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long idProducto;
    private String nombreProducto;
    private Double precioProducto;
    private Double ivaProducto;
    private Integer stockProducto;
    private Integer cantidad;

    public static ProductoDTO fromProducto(Producto producto){
        ProductoDTO dto = new ProductoDTO();
        dto.setNombreProducto(producto.getNombreProducto());
        dto.setPrecioProducto(producto.getPrecioProducto());
        dto.setIvaProducto(producto.getIvaProducto());
        dto.setStockProducto(producto.getStockProducto());
        return dto;
    }
}
