package com.ecomerce.products_service.services;


import com.ecomerce.products_service.model.dtos.ProductRequest;
import com.ecomerce.products_service.model.dtos.ProductResponse;
import com.ecomerce.products_service.model.entities.Product;

import java.util.List;

public interface ProductService {
    void addProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
}
