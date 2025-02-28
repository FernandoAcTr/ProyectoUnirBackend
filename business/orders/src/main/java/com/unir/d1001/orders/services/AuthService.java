package com.unir.d1001.orders.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.d1001.orders.dto.UserDto;
import com.unir.d1001.orders.utils.AppHTTPClient;

@Service
public class AuthService {

    @Autowired
    AppHTTPClient appHTTPClient;

    public Optional<UserDto> getUser(String token) {
        try {
            UserDto product = appHTTPClient.getAuthClient().get()
                    .uri("/auth/user")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();

            return Optional.of(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
