package com.example.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
public class Product implements java.io.Serializable {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private Long count;
}
