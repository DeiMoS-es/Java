package com.ecommerce_microservicios.products_service.utils;

import com.ecommerce_microservicios.products_service.model.entities.Product;
import com.ecommerce_microservicios.products_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Cargando produtos");
        if(productRepository.findAll().isEmpty()){
            productRepository.saveAll(List.of(
                    Product.builder().productName("Producto 1").productSku("0001").productDescription("Descripción del producto 1").productPrice(100.0).productStatus(true).build(),
                    Product.builder().productName("Producto 2").productSku("0002").productDescription("Descripción del producto 2").productPrice(200.0).productStatus(true).build(),
                    Product.builder().productName("Producto 3").productSku("0003").productDescription("Descripción del producto 3").productPrice(300.0).productStatus(true).build(),
                    Product.builder().productName("Producto 4").productSku("0004").productDescription("Descripción del producto 4").productPrice(400.0).productStatus(true).build()
            ));
        }
        log.info("Productos cargados");
    }
}
