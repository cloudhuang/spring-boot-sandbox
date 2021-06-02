package com.example.services;

import com.example.aggregates.Status;
import com.example.entity.Account;
import com.example.events.*;
import com.example.repository.AccountRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class AccountUpdater {
    private static final Logger LOG = LoggerFactory.getLogger(AccountUpdater.class);

    private final AccountRepository accountRepository;

    public AccountUpdater(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event) {
        LOG.debug("Handle {}",  event);
        Account account = new Account(event.getId(), event.getAccountBalance(), event.getCurrency(), Status.CREATED);
        accountRepository.save(account);
    }

    @EventHandler
    public void on(MoneyCreditedEvent event) {
        LOG.debug("Handle {}",  event);
        Account account = accountRepository.getOne(event.getId());
        account.setAccountBalance(account.getAccountBalance() + event.getCreditAmount());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(MoneyDebitedEvent event) {
        LOG.debug("Handle {}",  event);
        Account account = accountRepository.getOne(event.getId());
        account.setAccountBalance(account.getAccountBalance() - event.getDebitAmount());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event) {
        LOG.debug("Handle {}",  event);
        Account account = accountRepository.getOne(event.getId());
        account.setStatus(Status.ACTIVATED);
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountHeldEvent event) {
        LOG.debug("Handle {}",  event);
        Account account = accountRepository.getOne(event.getId());
        account.setStatus(Status.HOLD);
        accountRepository.save(account);
    }
}
