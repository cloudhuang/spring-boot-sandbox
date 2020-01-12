package com.example;

import com.example.domain.order.Order;
import com.example.domain.product.Product;
import com.example.domain.user.UserAccount;
import com.example.repository.order.OrderRepository;
import com.example.repository.product.ProductRepository;
import com.example.repository.user.UserAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class SpringBootMultipleDatasourceApplicationTests {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void should_save_user_account() {
        UserAccount userAccount = new UserAccount();
        userAccount.setName("Liping Huang");
        userAccount.setBalance(BigDecimal.valueOf(1000L));
        userAccountRepository.save(userAccount);
    }

    @Test
    public void should_save_product() {
        Product product = new Product();
        product.setName("Java编程思想");
        product.setCount(100L);
        product.setPrice(BigDecimal.valueOf(99L));
        productRepository.save(product);
    }

    @Test
    public void should_save_order() {
        Order order = new Order();
        order.setProductId(1L);
        order.setUserId(1L);
        order.setAmount(BigDecimal.valueOf(200L));
        orderRepository.save(order);
    }

    @Test
    public void should_create_order() throws Exception {

    }
}
