package com.example.services.queries;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public AccountQueryServiceImpl(QueryGateway queryGateway, EventStore eventStore) {
        this.queryGateway = queryGateway;
        this.eventStore = eventStore;
    }

    @Override
    public List<?> listEventsForAccount(String accountNumber) {
        return eventStore.readEvents(accountNumber).asStream().map(Message::getPayload).collect(Collectors.toList());
    }

    @Override
    public Double accountBalance(String accountNumber) {
        try {
            return queryGateway.query(new AccountBalanceQuery(accountNumber), AccountBalance.class).get(2, TimeUnit.SECONDS).getBalanceAmt();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        return 0D;
    }
}