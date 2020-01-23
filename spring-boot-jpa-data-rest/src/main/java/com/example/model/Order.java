package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private OrderStatus orderStatus;
    private String description;

    private Order() {
        this.id = null;
        this.orderStatus = OrderStatus.BEING_CREATED;
        this.description = "";
    }

    public Order(String description) {
        this();
        this.description = description;
    }
}
