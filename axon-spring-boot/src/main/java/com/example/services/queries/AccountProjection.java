package com.example.services.queries;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountProjection {

    private final AccountRepository repository;

    public AccountProjection(AccountRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    AccountBalance handle(AccountBalanceQuery accountBalanceQuery) {
        Account account = repository.findById(accountBalanceQuery.getAccountNumber()).orElse(new Account());

        return new AccountBalance(account.getAccountBalance());
    }
}
