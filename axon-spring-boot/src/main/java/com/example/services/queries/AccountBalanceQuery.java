package com.example.services.queries;

import lombok.Data;

@Data
public class AccountBalanceQuery {
    private final String accountNumber;

    public AccountBalanceQuery(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
