package com.spring.tracing.controllers;

import com.spring.tracing.model.User;
import com.spring.tracing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    // {"firstname":"personA","lastname":"personF", "email": "person.a@tutorial.com","password":"sasa"}
    @Autowired
    UserService userService;
    @Autowired

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
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
