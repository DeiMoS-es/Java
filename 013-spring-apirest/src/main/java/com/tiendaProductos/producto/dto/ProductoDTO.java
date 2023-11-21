package com.tiendaProductos.producto.dto;

import com.tiendaProductos.producto.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private Double precioProducto;
    private Integer stockProducto;
    private String tipoProducto;

    public static ProductoDTO fromProducto(Producto producto){
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombreProducto(producto.getNombreProducto());
        dto.setDescripcionProducto(producto.getDescripcionProducto());
        dto.setPrecioProducto(producto.getPrecioProducto());
        dto.setStockProducto(producto.getStockProducto());
        dto.setTipoProducto(producto.getTipoProducto());
        return dto;
    }
}
