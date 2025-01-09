package com.bipartite.v1.Model;

public class ColorUpdateRequest {
    private String featureId;

    //featureID could be null
    public void setFeatureId(String featureId){
        this.featureId=featureId;
    }
    public String getFeatureId(){
        return this.featureId;
    }
}
