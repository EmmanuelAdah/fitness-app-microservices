package com.server.userservice.services;

import com.server.userservice.data.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public String generateToken(User user) {
        List<String> roles = user.getAuthorities().stream()
                .map(authority -> Objects.requireNonNull(authority.getAuthority())
                        .replace("ROLE_", ""))
                .toList();

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("id", user.getId())
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .signWith(getSecretKey())
                .compact();
    }

    public SecretKey getSecretKey() {
        byte[] decodedKey = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}
