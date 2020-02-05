package com.example.web.payload;

import lombok.Data;

@Data
public class AuthTokenResponse {
    private String token;
    private String username;
}