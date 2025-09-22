package com.spring.tracing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/user")
public class UserController1 {

    private static Logger log = LoggerFactory.getLogger(UserController1.class);
    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/getemail")
    public String email(){
        log.info("GET email ");
        return "ahmad@gmail.com";
    }

    @GetMapping("/viewproducts")
    public String getproduct(){

        String M2Response=restTemplate.getForObject("http://amazone-product/product/getproducts",String.class);
        log.info("GET product"+M2Response);
        return "get respnse" + M2Response;
    }
}
