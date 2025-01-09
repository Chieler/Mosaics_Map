package com.bipartite.v1.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="featureColors")
public class FeatureColor {
    @Id
    private String id;
    @Indexed
    private String featureId;
    @Indexed
    private String userId;
    @Indexed
    private boolean isColored;
    public String getId(){return this.id;}
    public String getFeatureId(){return this.featureId;}
    public String getUserId(){return this.userId;}
    public void setId(String id){this.id = id;}
    public void setFeatureId(String featureId){this.featureId=featureId;}
    public void setUserId(String userId){this.userId = userId;}
    public boolean getIsColored(){return this.isColored;}
    public void setIsColored(){this.isColored=!this.isColored;}

}
