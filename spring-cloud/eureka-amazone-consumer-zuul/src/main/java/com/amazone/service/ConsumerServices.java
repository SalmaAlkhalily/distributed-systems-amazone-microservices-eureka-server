package com.amazone.service;

import com.amazone.entity.Product;
import com.amazone.entity.Search;

import java.util.ArrayList;
import java.util.List;

public class ConsumerServices {
    public List<Product> findProduct(Search searchQuery, List<Product> allProducts, String category)
    {
        List<Product> productsSearchRes=new ArrayList<>();
        for(Product p:allProducts){
            if(p.getCategory().equals(category)&&p.getName().equals(searchQuery.getSearchAbout()))
                productsSearchRes.add(p);
        }
        return productsSearchRes;
    }
}
