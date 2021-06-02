package com.example.controllers;

import com.example.services.queries.AccountQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/bank-accounts")
@Tag(name = "Account Queries", description = "Account Query Events Endpoint")
public class AccountQueryController {
    private final AccountQueryService accountQueryService;

    public AccountQueryController(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    @GetMapping("/{accountNumber}/events")
    public List<?> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber) {
        return accountQueryService.listEventsForAccount(accountNumber);
    }

    @GetMapping("/{accountNumber}/balance")
    public Double getAccountBalance(@PathVariable(value = "accountNumber") String accountNumber) {
        return accountQueryService.accountBalance(accountNumber);
    }
}