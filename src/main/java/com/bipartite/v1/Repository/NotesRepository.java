package com.bipartite.v1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.bipartite.v1.Model.Note;

@Repository
public interface NotesRepository extends MongoRepository<Note, String>{
    Optional<Note> findByCountryIdAndUserId(String countryId, String userId);
    List<Note> findAllByUserId(String userId);
    @Query("{'content': '<p><br></p>'}")
    List<Note> findUnecesssaryData();
    
}
