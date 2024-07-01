package com.ecommerce_microservicios.products_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long productId;
    private String productSku;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Boolean productStatus;
}
