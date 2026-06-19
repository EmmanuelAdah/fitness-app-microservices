package com.server.aiservice.services;

import com.server.aiservice.dtos.response.RecommendationResponse;
import com.server.aiservice.entities.Recommendation;
import com.server.aiservice.exceptions.RecommendationNotFoundException;
import com.server.aiservice.exceptions.UnauthorizedRecommendationAccessException;
import com.server.aiservice.repositories.RecommendationRepository;
import com.server.aiservice.services.interfaces.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepo;
    private final ModelMapper modelMapper;

    @Override
    public RecommendationResponse getRecommendation(String id, String userId) {

        Recommendation recommendation = recommendationRepo.findById(id)
                .orElseThrow(() -> new RecommendationNotFoundException("Recommendation not found"));

        if (!recommendation.getUserId().equals(userId)) {
            throw new UnauthorizedRecommendationAccessException("User not authorized");
        }

        return modelMapper.map(recommendation, RecommendationResponse.class);
    }

    @Override
    public List<RecommendationResponse> getAllRecommendations(String userId) {

        List<Recommendation> recommendations = recommendationRepo.findAllByUserId(userId);

        if (recommendations.isEmpty()) {
            throw new RecommendationNotFoundException("No recommendations found");
        }

        return recommendations.stream()
                .map( recommendation -> modelMapper.map(
                        recommendation, RecommendationResponse.class)
                ).toList();
    }

}
