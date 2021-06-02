package com.example.demo.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class WelcomeController {
    @GetMapping("/user")
    public ResponseEntity<?> user(@PathParam("name") String name) {
        return ResponseEntity.ok("Welcome " + name);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admin/user")
    public ResponseEntity<?> adminUser(@PathParam("name") String name) {
        return ResponseEntity.ok("Welcome " + name);
    }
}
