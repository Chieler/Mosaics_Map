package com.bipartite.v1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.bipartite.v1.Model.MarkedCountry;

@Repository
public interface MarkedCountryRepository extends MongoRepository<MarkedCountry, String>{
    Optional<MarkedCountry> findByMarkIdAndUserId(String markId, String userId);
    List<MarkedCountry> findAllByUserId(String userId);
    @Query("{'isMarked': false}")
    List<MarkedCountry> findUnnecessaryData();
} 
