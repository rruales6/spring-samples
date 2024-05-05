package com.example.apibanking.services;

import com.example.apibanking.entities.User;
import com.example.apibanking.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUser(String identification){
        return userRepository.findByIdentification(identification);
    }
}