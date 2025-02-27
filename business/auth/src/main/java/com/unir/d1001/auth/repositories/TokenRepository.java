package com.unir.d1001.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.d1001.auth.entities.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

    public Optional<Token> findByToken(String token);
}
