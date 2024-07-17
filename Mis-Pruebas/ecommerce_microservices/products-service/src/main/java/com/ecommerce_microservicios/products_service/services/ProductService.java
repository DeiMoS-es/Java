package com.ecommerce_microservicios.products_service.services;

import com.ecommerce_microservicios.products_service.model.dtos.ProductRequest;

import com.ecommerce_microservicios.products_service.model.dtos.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    void addProduct(ProductRequest productRequest);
    List<ProductResponse> listarAllProducts();
    ProductResponse getProductById(Long id);
    void deleteProduct(Long id);
}
