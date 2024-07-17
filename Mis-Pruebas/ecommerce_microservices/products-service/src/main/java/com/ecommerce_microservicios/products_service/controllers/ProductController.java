package com.ecommerce_microservicios.products_service.controllers;

import com.ecommerce_microservicios.products_service.exception.ProductException;
import com.ecommerce_microservicios.products_service.model.dtos.ProductRequest;
import com.ecommerce_microservicios.products_service.model.dtos.ProductResponse;
import com.ecommerce_microservicios.products_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    @PostMapping("/save")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        try {
            this.productService.addProduct(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
        } catch (ProductException e){
            // Captura la excepción de tipo ProductException
            log.error("Error de validación: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
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
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        ProductResponse product = this.productService.getProductById(id);
        if(product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto con id: " + id + " no existe");
        }
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try {
            this.productService.deleteProduct(id);
            return ResponseEntity.ok("Producto eliminado");
        } catch (ProductException e) {
            // Captura específicamente la ProductException para manejar el caso de productos no encontrados
            log.error("Error al eliminar el producto: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error deleting product");
        }
    }
}
