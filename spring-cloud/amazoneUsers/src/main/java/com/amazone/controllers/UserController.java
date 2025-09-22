package com.amazone.controllers;
import com.amazone.model.User;
import com.amazone.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    // {"firstname":"personA","lastname":"personF", "email": "person.a@tutorial.com","password":"sasa"}
    @Autowired
    UserService userService;

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") int id) {

        return userService.getUserById(id);
    }

    @PostMapping("/users/login")
    public User login(@RequestBody User user) {
        return userService.getUserByEmail(user.getEmail());
    }
    /*
    @DeleteMapping("/users/{id}/delete")
    private void deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
    }*/

    @PostMapping("/users/add")
    public int saveUser(@RequestBody User user) {

        if(userService.getUserByEmail(user.getEmail())!=null)
        {

            return 0;
        }
        userService.save(user);
        return user.getId();}

}
