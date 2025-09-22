package com.amazone.service;

import com.amazone.model.Search;


public class SearchService {
    public String RepaireData(Search searchQuery)
    {
        if(searchQuery.getSearchBy()==1)
            return "clothes";
        else if(searchQuery.getSearchBy()==2)
            return "food";
        else if(searchQuery.getSearchBy()==2)
            return "accessories";
        return "things";
    }

}
