package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinStarterApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinStarterApplication>(*args)
}
