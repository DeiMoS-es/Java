package com.ecomerce.products_service.services.impl;

import com.ecomerce.products_service.model.dtos.ProductRequest;
import com.ecomerce.products_service.model.dtos.ProductResponse;
import com.ecomerce.products_service.model.entities.Product;
import com.ecomerce.products_service.repositories.ProductRepository;
import com.ecomerce.products_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public void addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .productDescription(productRequest.getProductDescription())
                .productPrice(productRequest.getProductPrice())
                .productStatus(productRequest.getProductStatus())
                .build();
        productRepository.save(product);
        log.info("Product added successfully: {}", product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .productStatus(product.getProductStatus())
                .build();
    }
}
