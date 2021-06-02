package com.example.events;

import lombok.Getter;

@Getter
public class MoneyDebitedEvent extends BaseEvent<String> {
    private final double debitAmount;
    private final String currency;

    public MoneyDebitedEvent(String id, double debitAmount, String currency) {
        super(id);
        this.debitAmount = debitAmount;
        this.currency = currency;
    }
}