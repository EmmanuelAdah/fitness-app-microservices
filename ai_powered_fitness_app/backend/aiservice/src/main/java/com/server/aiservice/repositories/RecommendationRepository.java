package com.server.aiservice.repositories;

import com.server.aiservice.entities.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation,String> {

    List<Recommendation> findAllByUserId(String userId);
}
