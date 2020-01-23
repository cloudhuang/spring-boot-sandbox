package com.example.loader;

import com.example.model.Order;
import com.example.model.Product;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DatasetLoader {
    @Bean
    public CommandLineRunner init(ProductRepository productRepository, OrderRepository orderRepository) {
        return args -> {
            productRepository.save(Product.builder().name("Java编程思想").price(BigDecimal.valueOf(99L)).count(100L).build());
            productRepository.save(Product.builder().name("沉思录").price(BigDecimal.valueOf(59L)).count(100L).build());

            orderRepository.save(new Order("Java编程思想"));
            orderRepository.save(new Order("沉思录"));
        };
    }
}
