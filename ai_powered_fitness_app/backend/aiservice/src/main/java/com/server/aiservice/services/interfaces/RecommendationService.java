package com.server.aiservice.services.interfaces;

import com.server.aiservice.dtos.response.RecommendationResponse;
import java.util.List;

public interface RecommendationService {

    RecommendationResponse getRecommendation(String id, String userId);
    List<RecommendationResponse> getAllRecommendations(String userId);
}
