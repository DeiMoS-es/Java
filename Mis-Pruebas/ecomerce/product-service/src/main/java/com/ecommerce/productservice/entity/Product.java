package com.ecommerce.productservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotBlank(message = "The name of the product cannot be empty")
    @Size(min = 3, max = 50, message = "The name must be between 3 and 50 characters")
    @Column(unique = true)
    private String name;
    private String description;
    @Min(value = 0, message = "The price must be greater than 0")
    private double price;
    @Min(value = 0, message = "The quantity must be greater than 0")
    private int quantity;

}
