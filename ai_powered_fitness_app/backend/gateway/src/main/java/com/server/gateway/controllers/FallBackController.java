package com.server.gateway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/user")
    public Mono<String> userServiceFallback() {
        return Mono.just("User Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/activity")
    public Mono<String> activityServiceFallback() {
        return Mono.just("Activity Service is currently unavailable. Please try again later.");
    }
}
