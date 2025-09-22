package com.spring.tracing.service;


import com.spring.tracing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService  {



    @Autowired
    RestTemplate restTemplate;

    public String Login(User user) {
        String loginResponse=restTemplate.postForObject("http://amazone-user/users/login",user,String.class);
        System.out.println("login response--------->"+loginResponse);
return  loginResponse;
    }
    public void createAccount(User user) {
        String createAccountResponse=restTemplate.postForObject("http://Amazone-user/users/add",user,String.class);
        System.out.println("create account response--------->"+createAccountResponse);

    }


}

