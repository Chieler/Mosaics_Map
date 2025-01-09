package com.bipartite.v1.Model;

public class NoteUpdateRequest {
    private String countryId;
    private String content;
    public void setContent(String content){this.content = content;}
    public void seCountryId(String countryId){this.countryId = countryId;}
    public String getCountryId(){return this.countryId; }
    public String getContent(){return this.content;}
}
