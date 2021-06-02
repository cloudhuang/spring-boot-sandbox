CQRS - Event Sourcing with Axon framework & Spring Boot
=========================================================



### H2 Database console
http://localhost:8080/h2-console

### OpenAPI browser
http://localhost:8080/swagger-ui.html

## Troubleshooting

### No connection to AxonServer available
- explicitly define the EmbeddedEventStore in your application context. This approach will allow you to use the event and query bus by Axon Server.
- exclude the axon-server-connector dependency in your pom/gradle file. This will give you the defaults you were used to in Axon.

### Unknown entity: org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry
Add the `@EntityScan` which include the `org.axonframework.eventsourcing.eventstore.jpa` package and the project entity package
```
@EntityScan(basePackages = {"com.example.entity",
                            "org.axonframework.eventsourcing.eventstore.jpa",
                            "org.axonframework.eventhandling.tokenstore.jpa"
})
```