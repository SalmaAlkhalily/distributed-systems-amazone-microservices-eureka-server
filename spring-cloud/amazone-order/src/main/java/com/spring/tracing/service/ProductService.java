package com.spring.tracing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Autowired
    RestTemplate restTemplate;

    public float getProductPrice(int id) {
        String M2Response=restTemplate.getForObject("http://amazone-product/products/getprice",String.class);
        System.out.println("Get product price =============================    "+M2Response);

        return Float.parseFloat(M2Response);
    }


}

