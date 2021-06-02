package com.example.demo.web.controller;

import com.example.demo.configuration.security.service.AuthenticationService;
import com.example.demo.web.payload.request.LoginRequest;
import com.example.demo.web.payload.response.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * 登录
     */
    @PostMapping(value = "/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        String token = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
        accessTokenResponse.setToken(token);
        return ResponseEntity.ok(accessTokenResponse);
    }
}