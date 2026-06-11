package com.server.gateway.filter;


import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // Public endpoints (login, register, health)
        String path = request.getURI().getPath();
        if (path.startsWith("/api/v1/auth/login") ||
                path.startsWith("/api/v1/auth/register") ||
                path.equals("/actuator/health")) {
            return chain.filter(exchange);
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            // You can add custom claims extraction here if needed
        }

        return chain.filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder.clearContext());
    }
}
