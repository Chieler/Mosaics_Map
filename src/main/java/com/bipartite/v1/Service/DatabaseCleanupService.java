package com.bipartite.v1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bipartite.v1.Model.FeatureColor;
import com.bipartite.v1.Model.MarkedCountry;
import com.bipartite.v1.Model.Note;
import com.bipartite.v1.Repository.FeatureColorRepository;
import com.bipartite.v1.Repository.MarkedCountryRepository;
import com.bipartite.v1.Repository.NotesRepository;

@Service
public class DatabaseCleanupService {
    @Autowired
    MarkedCountryRepository markedCountryRepository;
    @Autowired
    FeatureColorRepository featureColorRepository;
    @Autowired
    NotesRepository notesRepository;
    public void pruneUnnecessaryData(){
        List<FeatureColor> unecessaryFeatureColorEntries = featureColorRepository.findUnnecessaryData();
        featureColorRepository.deleteAll(unecessaryFeatureColorEntries);
        List<MarkedCountry> unecessaryMarkedCountryEntries = markedCountryRepository.findUnnecessaryData();
        markedCountryRepository.deleteAll(unecessaryMarkedCountryEntries);
        List<Note> emptyNotes = notesRepository.findUnecesssaryData();
        notesRepository.deleteAll(emptyNotes);
    }
}
