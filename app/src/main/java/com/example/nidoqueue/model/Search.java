package com.example.nidoqueue.model;

public class Search {
    private String search;

    public Search(String search){
        this.search = search;
    }
    public String searchName(String search){
        for(int i = 0; i<2; i++){
            if(search==""){
                return "exp";
            }else{
                return "No Results";
            }
        }
        return "No Results";
    }
}
