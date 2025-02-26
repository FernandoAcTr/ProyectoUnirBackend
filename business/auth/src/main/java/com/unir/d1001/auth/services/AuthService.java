package com.unir.d1001.auth.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.unir.d1001.auth.dto.RegisterRequest;
import com.unir.d1001.auth.dto.TokenResponse;
import com.unir.d1001.auth.entities.Token;
import com.unir.d1001.auth.entities.User;
import com.unir.d1001.auth.repositories.TokenRepository;
import com.unir.d1001.auth.repositories.UserRepository;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public AuthService(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            JwtService jwtService,
            TokenRepository tokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
    }

    public TokenResponse registerUser(RegisterRequest data) {
        User user = User.builder()
                .email(data.email())
                .password(data.password())
                .name(data.name())
                .password(passwordEncoder.encode(data.password()))
                .build();

        User savedUser = userRepository.save(user);
        String token = jwtService.createtoken(savedUser);
        String refreshToken = jwtService.createRefreshToken(savedUser);
        SaveToken(savedUser, refreshToken);
        return new TokenResponse(token, refreshToken);
    }

    private void SaveToken(User user, String token) {
        tokenRepository.save(Token.builder()
                .user(user)
                .token(token)
                .build());
    }
}
