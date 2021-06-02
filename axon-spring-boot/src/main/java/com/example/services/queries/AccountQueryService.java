package com.example.services.queries;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface AccountQueryService {
    public List<?> listEventsForAccount(String accountNumber);

    public Double accountBalance(String accountNumber);
}