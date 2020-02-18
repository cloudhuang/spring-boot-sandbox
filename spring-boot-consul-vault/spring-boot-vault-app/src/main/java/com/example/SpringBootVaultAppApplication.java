package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootVaultAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVaultAppApplication.class, args);
    }

    @RestController
    @RequestMapping("/")
    class VaultDemoRestController {
        @Value("${name:Liping Huang}")
        private String name;

        @GetMapping("/welcome")
        public String welcome() {
            return name;
        }
    }
}
