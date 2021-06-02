package com.example.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AxonAggregateConfig {
    @Bean
    public JpaEventStorageEngine eventStorageEngine(EntityManagerProvider entityManagerProvider, TransactionManager transactionManager) {
        return new JpaEventStorageEngine.Builder().entityManagerProvider(entityManagerProvider).transactionManager(transactionManager).build();
    }
}