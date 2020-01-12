package com.example.service;

import com.example.domain.order.Order;
import com.example.domain.product.Product;
import com.example.domain.user.UserAccount;
import com.example.exception.NoEnoughBalanceException;
import com.example.exception.NoEnoughProductException;
import com.example.repository.order.OrderRepository;
import com.example.repository.product.ProductRepository;
import com.example.repository.user.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.Instant;

@Service
public class OrderService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public void makeOrder(Long userId, Long productId, int count) {
        UserAccount userAccount = userAccountRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException(String.format("The user account[%s] not found." , userId)));

        Product product = productRepository.findById(productId).orElseThrow(() ->
                new EntityNotFoundException(String.format("The product[%s] not found", productId)));


        if (product.getCount() < count) {
            throw new NoEnoughProductException();
        }

        BigDecimal balance = userAccount.getBalance();
        BigDecimal price = product.getPrice();
        if (balance.compareTo(price) < 0) {
            throw new NoEnoughBalanceException();
        }

        balance = balance.subtract(price);
        userAccount.setBalance(balance);
        userAccountRepository.save(userAccount);

        product.setCount(product.getCount() - count);
        productRepository.save(product);

        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setAmount(price.multiply(BigDecimal.valueOf(count)));
        order.setOrderedAt(Instant.now());
        orderRepository.save(order);
    }
}
