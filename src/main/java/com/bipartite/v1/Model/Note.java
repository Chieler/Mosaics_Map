package com.bipartite.v1.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="notes")
public class Note {
    @Id
    private String id;
    @Indexed
    private String countryId;
    @Indexed
    private String userId;
    @Indexed
    private String content;
    public void setContent(String content){this.content=content;}
    public void setId(String id){this.id=id;}
    public void setCountryId(String countryId){this.countryId = countryId;}
    public void setUserId(String userId){this.userId = userId;}
    public String getId(){return this.id;}
    public String getCountryId(){return this.countryId;}
    public String getUserId(){return this.userId;}
    public String getContent(){return this.content;}
}
