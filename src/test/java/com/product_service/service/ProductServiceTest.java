package com.product_service.service;

import com.product_service.model.Product;
import com.product_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 🔹 Test: get product by ID
    @Test
    void testProductById() {
        Product product = new Product(1, "Test Product","Description", 100);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.productById(1);
        assertTrue(result.isPresent());
        assertEquals("Test Product", result.get().getName());
    }

    // 🔹 Test: get all products paginated
    @Test
    void testGetAllProducts() {
        Product p1 = new Product(1, "Product A","Desc", 10);
        Product p2 = new Product(2, "Product B","Desc",20);

        List<Product> page1 = Arrays.asList(p1);
        List<Product> page2 = Arrays.asList(p2);

        Page<Product> page1Data = new PageImpl<>(page1, PageRequest.of(0, 20), 40);
        Page<Product> page2Data = new PageImpl<>(page2, PageRequest.of(1, 20), 40);
        Page<Product> page3Data = new PageImpl<>(Collections.emptyList(), PageRequest.of(2, 20), 40);

        when(productRepository.findAll(PageRequest.of(0, 20))).thenReturn(page1Data);
        when(productRepository.findAll(PageRequest.of(1, 20))).thenReturn(page2Data);
        when(productRepository.findAll(PageRequest.of(2, 20))).thenReturn(page3Data);

        List<Product> result = productService.getAllProducts();
        assertEquals(2, result.size());
    }

    // 🔹 Test: save product
    @Test
    void testSave() {
        Product product = new Product(1, "New Product","Desc", 50);
        when(productRepository.save(product)).thenReturn(product);

        Product saved = productService.save(product);
        assertNotNull(saved);
        assertEquals("New Product", saved.getName());
    }
}
