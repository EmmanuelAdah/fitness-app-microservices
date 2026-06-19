package com.server.gateway.filter;


import com.server.gateway.services.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {
    private final JwtService jwtService;

    @Override
    public @NonNull Mono<Void> filter(
            ServerWebExchange exchange,
            @NonNull GatewayFilterChain chain) {

        String header =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("Authorization");

        if (header == null ||
                !header.startsWith("Bearer ")) {

            return chain.filter(exchange);
        }

        String token = header.substring(7);

        Claims claims = jwtService.extractClaims(token);

        if (claims == null || !jwtService.isTokenExpired(claims.getExpiration())) {
            return chain.filter(exchange);
        }

        ServerHttpRequest request =
                exchange.getRequest()
                        .mutate()
                        .header(
                                "X-User-Id",
                                claims.getSubject()
                        )
                        .header(
                                "X-User-Email",
                                claims.get("email", String.class)
                        )
                        .header(
                                "X-User-Role",
                                claims.get("role", String.class)
                        )
                        .build();

        return chain.filter(
                exchange.mutate()
                        .request(request)
                        .build()
        );
    }
}