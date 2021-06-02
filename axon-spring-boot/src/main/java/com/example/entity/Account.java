package com.example.entity;

import com.example.aggregates.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    private String id;
    private Double accountBalance;
    private String currency;
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
