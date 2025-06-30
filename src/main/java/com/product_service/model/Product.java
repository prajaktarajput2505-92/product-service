package com.product_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Product {
    public Product(long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        Description = description;
        this.price = price;
    }

    @Id
    @Min(value = 1, message = "ID must be greater than 0")
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String Description;

    @Positive(message = "Price must be positive")
    private double price;
}
