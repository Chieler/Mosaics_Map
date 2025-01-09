package com.bipartite.v1.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="markedCountries")
public class MarkedCountry {
    @Id
    private String id;
    @Indexed
    private String markId;
    @Indexed
    private String userId;
    @Indexed
    private boolean isMarked;
    public void setIsMarked(){isMarked=!isMarked;}
    public void setId(String id){this.id=id;}
    public void setMarkId(String markId){this.markId = markId;}
    public void setUserId(String userId){this.userId = userId;}
    public String getId(){return this.id;}
    public boolean getIsMarked(){ return this.isMarked;}
    public String getMarkId(){return this.markId;}
    public String getUserId(){return this.userId;}

}
