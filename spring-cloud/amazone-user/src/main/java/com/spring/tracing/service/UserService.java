package com.spring.tracing.service;

import com.spring.tracing.model.User;
import com.spring.tracing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public Optional< User> getUserById(Integer id) {
        return userRepository.findById(id);
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
        userRepository.deleteById(id);
    }
}

