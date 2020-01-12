package com.example.exception;

public class NoEnoughBalanceException extends RuntimeException {
    public NoEnoughBalanceException() {
    }

    public NoEnoughBalanceException(String message) {
        super(message);
    }

    public NoEnoughBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEnoughBalanceException(Throwable cause) {
        super(cause);
    }
}
