package com.bipartite.v1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bipartite.v1.Model.FeatureColor;
@Repository
public interface FeatureColorRepository extends MongoRepository<FeatureColor, String>{
    Optional<FeatureColor> findByFeatureIdAndUserId(String featureId, String userId);
    List<FeatureColor> findAllByUserId(String userId);
    @Query("{'isColored': false}")
    List<FeatureColor> findUnnecessaryData();
}