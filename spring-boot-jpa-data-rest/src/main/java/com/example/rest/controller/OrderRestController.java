package com.example.rest.controller;

import com.example.exception.OrderNotFoundException;
import com.example.model.Order;
import com.example.model.OrderStatus;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.model.OrderStatus.valid;

@BasePathAwareController
public class OrderRestController {
    @Autowired
    private OrderRepository repository;

    @PostMapping("/orders/{id}/pay")
    public ResponseEntity<?> pay(@PathVariable Long id) {

        Order order = this.repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        if (valid(order.getOrderStatus(), OrderStatus.PAID_FOR)) {
            order.setOrderStatus(OrderStatus.PAID_FOR);
            return ResponseEntity.ok(repository.save(order));
        }

        return ResponseEntity.badRequest()
                .body("Transitioning from " + order.getOrderStatus() + " to " + OrderStatus.PAID_FOR + " is not valid.");
    }

    @PostMapping("/orders/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {

        Order order = this.repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        if (valid(order.getOrderStatus(), OrderStatus.CANCELLED)) {
            order.setOrderStatus(OrderStatus.CANCELLED);
            return ResponseEntity.ok(repository.save(order));
        }

        return ResponseEntity.badRequest()
                .body("Transitioning from " + order.getOrderStatus() + " to " + OrderStatus.CANCELLED + " is not valid.");
    }

    @PostMapping("/orders/{id}/fulfill")
    public ResponseEntity<?> fulfill(@PathVariable Long id) {

        Order order = this.repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        if (valid(order.getOrderStatus(), OrderStatus.FULFILLED)) {
            order.setOrderStatus(OrderStatus.FULFILLED);
            return ResponseEntity.ok(repository.save(order));
        }

        return ResponseEntity.badRequest()
                .body("Transitioning from " + order.getOrderStatus() + " to " + OrderStatus.FULFILLED + " is not valid.");
    }
}
