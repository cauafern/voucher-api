package com.caua.voucher_api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.caua.voucher_api.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtService {
    @Value("${api.security.secret}")
    private String secretKey;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("email", user.getEmail())
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .sign(algorithm);
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        try {
            return JWT.require(algorithm)
                     .build()
                     .verify(token)
                     .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }
}