package com.product_service.service;

import com.product_service.model.Product;
import com.product_service.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductRepository productRepository;

    public Optional<Product> productById(int id)
    {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        int batchSize = 20;
        int page = 0;
        List<Product> result = new ArrayList<>();

        Page<Product> current;
        do {
            Pageable pageable = PageRequest.of(page, batchSize);
            current = productRepository.findAll(pageable);
            result.addAll(current.getContent());
            page++;
        } while (!current.isLast());

        return result;
    }

    public Product save(@Valid Product product) {
        return  productRepository.save(product);
    }
}
