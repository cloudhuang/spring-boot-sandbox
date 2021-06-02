package com.example.services.commands;

import com.example.services.commands.dto.AccountCreateDTO;
import com.example.services.commands.dto.MoneyCreditDTO;
import com.example.services.commands.dto.MoneyDebitDTO;

import java.util.concurrent.CompletableFuture;

public interface AccountCommandService {
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);

    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);

    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}