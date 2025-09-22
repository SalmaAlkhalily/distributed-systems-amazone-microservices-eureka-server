package com.amazone.service;
import com.amazone.model.User;
import com.amazone.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;



@Service
public class UserService  {

    @Autowired
    UserRepository userRepository;
//    @Autowired
//    CacheManager cacheManager;
   // @CachePut("users")
//    @HystrixCommand(fallbackMethod = "getAllUsersFallBack",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
//            })
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }
//    public List<User> getAllUsersFallBack() {  Cache.ValueWrapper w = cacheManager.getCache("users").get(1);
//        if (w != null)
//        { List<User> users = new ArrayList<>();
//            System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
//            userRepository.findAll().forEach(user -> users.add(user));
//            return users;}
//        System.out.println("tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
//        return new ArrayList<>();
//
//    }
    public User getUserById(int id) {
        return userRepository.findOne(id);
    }
    public User getUserByEmail(String email) {
        List<User> users=this.getAllUsers();
        User myuser=null;
        for (int i=0;i<users.size();i++){
            if(users.get(i).getEmail().equals(email)){
                myuser=users.get(i);}
        }return myuser;
    }
    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }
}

