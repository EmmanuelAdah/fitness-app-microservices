package com.server.aiservice.dtos.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class RecommendationResponse {

    private String id;
    private String activityId;
    private String userId;
    private Map<String, Object> improvements;
    private Map<String, Object> suggestions;
    private List<String> safetyMeasures;
    private String recommendation;

    private LocalDateTime createdAt;
}
