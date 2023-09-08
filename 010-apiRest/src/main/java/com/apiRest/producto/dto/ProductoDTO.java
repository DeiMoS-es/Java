package com.apiRest.producto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private String nombreProducto;
    private Double precioProducto;
    private Double ivaProducto;
}
