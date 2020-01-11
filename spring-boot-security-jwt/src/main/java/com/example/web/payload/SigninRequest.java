package com.example.web.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SigninRequest {
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}
