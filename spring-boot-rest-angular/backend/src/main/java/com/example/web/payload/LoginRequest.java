package com.example.web.payload;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}