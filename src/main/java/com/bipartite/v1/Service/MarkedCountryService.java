package com.bipartite.v1.Service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bipartite.v1.Model.MarkedCountry;
import com.bipartite.v1.Repository.MarkedCountryRepository;

@Service
public class MarkedCountryService {
    @Autowired
    MarkedCountryRepository markedCountryRepository;
    @Async
    public CompletableFuture<MarkedCountry> saveMarkedCountryAsync(MarkedCountry country){
        return CompletableFuture.supplyAsync(()->markedCountryRepository.save(country));
    }
}
