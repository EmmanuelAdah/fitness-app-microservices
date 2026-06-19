package com.server.aiservice.controllers;

import com.server.aiservice.dtos.response.RecommendationResponse;
import com.server.aiservice.services.RecommendationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationServiceImpl recommendationService;

    @GetMapping("/{id}")
    public ResponseEntity<RecommendationResponse> getRecommendation(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId
            ){

        return ResponseEntity.ok(
                recommendationService
                        .getRecommendation(id, userId)
        );
    }

    @GetMapping
    public ResponseEntity<List<RecommendationResponse>> getRecommendations(
            @RequestHeader("X-User-Id") String userId
    ){
        return ResponseEntity.ok(
            recommendationService.getAllRecommendations(userId)
        );
    }
}
