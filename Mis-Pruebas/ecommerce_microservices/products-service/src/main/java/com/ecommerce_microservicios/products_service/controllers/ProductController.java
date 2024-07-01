package com.ecommerce_microservicios.products_service.controllers;

import com.ecommerce_microservicios.products_service.model.dtos.ProductRequest;
import com.ecommerce_microservicios.products_service.model.dtos.ProductResponse;
import com.ecommerce_microservicios.products_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/save")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        try {
            this.productService.addProduct(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error adding product");
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> listarAllProducts(){
        List<ProductResponse> products = this.productService.listarAllProducts();
        return ResponseEntity.ok(products);
    }
}
