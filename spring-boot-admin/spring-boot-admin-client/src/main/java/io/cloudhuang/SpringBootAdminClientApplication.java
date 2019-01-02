package io.cloudhuang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootAdminClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminClientApplication.class, args);
    }
}

@RestController
class WelcomeController {
    @GetMapping(path = "/welcome")
    public String welcome() {
        return "Welcome";
    }
}