package com.ecommerce_microservicios.products_service.services.impl;

import com.ecommerce_microservicios.products_service.exception.ProductException;
import com.ecommerce_microservicios.products_service.model.dtos.ProductRequest;
import com.ecommerce_microservicios.products_service.model.dtos.ProductResponse;
import com.ecommerce_microservicios.products_service.model.entities.Product;
import com.ecommerce_microservicios.products_service.repositories.ProductRepository;
import com.ecommerce_microservicios.products_service.services.ProductService;
import com.ecommerce_microservicios.products_service.utils.MensajesEstado;
import com.ecommerce_microservicios.products_service.utils.ValidationProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public void addProduct(ProductRequest productRequest) {
        try{
            Optional<Product> existProduct = productRepository.findByProductName(productRequest.getProductName());
            if(existProduct.isEmpty()){
                ValidationProduct.validarProducto(productRequest);
                //TODO: implementar fecha de alta de producto
                Product product = Product.builder()
                        .productSku(productRequest.getProductSku())
                        .productName(productRequest.getProductName())
                        .productDescription(productRequest.getProductDescription())
                        .productPrice(productRequest.getProductPrice())
                        .productStatus(true)
                        .build();
                productRepository.save(product);
                MensajesEstado.mensajeConfirmacion("Producto añadido correctamente");
            }else{
                throw new ProductException("El producto con el nombre: " + productRequest.getProductName() + " ya existe.");
            }
        }catch (ProductException e){
            log.error("Error de validación: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<ProductResponse> listarAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productSku(product.getProductSku())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .productStatus(product.getProductStatus())
                .build();
    }
}
