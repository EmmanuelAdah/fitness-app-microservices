package com.server.aiservice.utils;

import com.server.aiservice.exceptions.RecommendationNotFoundException;
import com.server.aiservice.exceptions.UnauthorizedRecommendationAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(RecommendationNotFoundException.class)
    public ResponseEntity<String> handleException(RecommendationNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedRecommendationAccessException.class)
    public ResponseEntity<String> handleException(UnauthorizedRecommendationAccessException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
