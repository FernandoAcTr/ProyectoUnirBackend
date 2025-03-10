package com.unir.d1001.auth.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unir.d1001.auth.dto.LoginRequest;
import com.unir.d1001.auth.dto.RefreshTokenRequest;
import com.unir.d1001.auth.dto.RegisterRequest;
import com.unir.d1001.auth.dto.TokenResponse;
import com.unir.d1001.auth.entities.User;
import com.unir.d1001.auth.services.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

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

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshTokenRequest request) {
        var response = authService.refreshToken(request.refreshToken());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public User getMethodName(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        return authService.getUserFromToken(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        authService.logout(token);

        return ResponseEntity.ok().build();
    }
}
