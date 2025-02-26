package com.unir.d1001.auth.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unir.d1001.auth.dto.LoginRequest;
import com.unir.d1001.auth.dto.RegisterRequest;
import com.unir.d1001.auth.dto.TokenResponse;
import com.unir.d1001.auth.services.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }   

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> postMethodName(@RequestBody RegisterRequest entity) {
        var response = authService.registerUser(entity);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest entity) {
        var response = authService.login(entity);

        return ResponseEntity.ok(response);
    }
}
