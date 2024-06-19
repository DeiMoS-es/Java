package com.ecommerce.productservice.service.impl;

import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Optional<Product> buscarProductoPorId(Long idProducto) {
        return productRepository.findById(idProducto);
    }
}
