package com.unir.d1001.auth.services;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.unir.d1001.auth.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret}")
    private String secret;

    @Value("${application.security.jwt.expiration}")
    private Long expiration;

    @Value("${application.security.jwt.refresh_token.expiration}")
    private Long refreshExpiration;

    public String createtoken(User user) {
        return buildToken(user, expiration);
    }

    public String createRefreshToken(User user) {
        return buildToken(user, refreshExpiration);
    }

    private String buildToken(User user, Long expiration) {
        return Jwts.builder()
                .id(user.getId().toString())
                .subject(user.getEmail())
                .claims(Map.of("name", user.getName()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
