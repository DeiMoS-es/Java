package com.ecommerce_microservicios.products_service.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@Builder
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    @Size(min = 4, max = 10)
    @Column(nullable = false, length = 10)
    private String sku;
    @NotNull
    @Size(min = 4, max = 50)
    @Column(nullable = false, length = 50)
    private String productName;

    @Size(min = 4, max = 255)
    @Column(nullable = false, length = 255)
    private String productDescription;
    @NotNull
    @Min(1)
    private Double productPrice;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
