package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootMonitoringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMonitoringAppApplication.class, args);
    }

    @RestController
    class WelcomeRestController {
        @GetMapping("/welcome")
        public String welcome() {
            return "Welcome";
        }
    }

}
