package com.ecommerce_microservicios.products_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que se encarga de manejar las peticiones de productos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String productSku;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Boolean productStatus;
}
