package com.example;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class AxonSpringApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testAppendBoomError() {
        Flux<Integer> flux = Flux.range(1, 10)
                .log()
                .take(3);
        flux.subscribe();
    }
}
