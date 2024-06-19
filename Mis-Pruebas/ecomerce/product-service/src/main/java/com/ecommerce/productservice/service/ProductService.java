package com.ecommerce.productservice.service;

import com.ecommerce.productservice.entity.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> buscarProductoPorId(Long idProducto);
}
