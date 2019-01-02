package com.example.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/welcome")
class WelcomeRestController {
    @GetMapping
    fun welcome(@RequestParam("name", defaultValue = "world") name: String): String {
        return "Welcome ${name}"
    }
}