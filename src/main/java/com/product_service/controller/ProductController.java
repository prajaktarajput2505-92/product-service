package com.product_service.controller;

import com.product_service.model.Product;
import com.product_service.model.ProductDTO;
import com.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id)
    {
        Optional<Product> product = productService.productById(id);

        if (product.isPresent()) {
            Product p = product.get();
            ProductDTO productDTO = new ProductDTO(p.getId(), p.getName(),p.getPrice());
            return ResponseEntity.ok(productDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Product> getAllProduct()
    {
        return productService.getAllProducts();
    }

}
