package com.example.service;

import com.example.domain.product.Product;
import com.example.domain.user.UserAccount;
import com.example.repository.order.OrderRepository;
import com.example.repository.product.ProductRepository;
import com.example.repository.user.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        UserAccount userAccount = new UserAccount();
        userAccount.setName("Liping Huang");
        userAccount.setBalance(BigDecimal.valueOf(1000L));
        userAccountRepository.save(userAccount);

        Product product = new Product();
        product.setName("Java编程思想");
        product.setCount(100L);
        product.setPrice(BigDecimal.valueOf(99L));
        productRepository.save(product);
    }

    @Test
    void makeOrder() {
        orderService.makeOrder(1L, 1L, 10);

        orderRepository.findAll().forEach(order -> {
            assertEquals(order.getAmount().longValue(), 990.00);
        });
    }
}