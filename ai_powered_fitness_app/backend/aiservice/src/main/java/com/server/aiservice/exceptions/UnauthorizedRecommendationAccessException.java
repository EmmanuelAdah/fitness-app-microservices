package com.server.aiservice.exceptions;

public class UnauthorizedRecommendationAccessException extends RuntimeException {
    public UnauthorizedRecommendationAccessException(String message) {
        super(message);
    }
}
