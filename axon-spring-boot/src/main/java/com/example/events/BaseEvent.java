package com.example.events;

import lombok.Getter;

@Getter
public class BaseEvent<T> {
    public final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}