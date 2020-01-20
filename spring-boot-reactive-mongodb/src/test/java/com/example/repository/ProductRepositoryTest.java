package com.example.repository;

import com.example.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setName("Java编程思想");
    }

    @AfterEach
    void tearDown() {
        productRepository.delete(product);
    }

    @Test
    public void should_save_product() throws Exception {
        productRepository.save(product);
    }
}