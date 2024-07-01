package com.ecommerce_microservicios.products_service.repositories;

import com.ecommerce_microservicios.products_service.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);
}
