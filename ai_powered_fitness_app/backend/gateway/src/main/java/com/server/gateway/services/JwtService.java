package com.server.gateway.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("jwt.secret.key")
    private String secretKey;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
