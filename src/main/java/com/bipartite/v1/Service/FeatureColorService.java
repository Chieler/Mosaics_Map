package com.bipartite.v1.Service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bipartite.v1.Model.FeatureColor;
import com.bipartite.v1.Repository.FeatureColorRepository;

@Service
public class FeatureColorService {
    @Autowired
    FeatureColorRepository featureColorRepository;
    @Async
    public CompletableFuture<FeatureColor> saveFeatureColorAsync(FeatureColor color){
        return CompletableFuture.supplyAsync(() -> featureColorRepository.save(color));
    }
}
