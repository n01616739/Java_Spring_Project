package com.finalproject.demo.service;

import com.finalproject.demo.entity.User;
import com.finalproject.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public User registerUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);  // Password is stored as plain text for now (you can encrypt it later)
        user.setRole(role);  // Assign role

        return userRepository.save(user);  // Save user to the database
    }

    // Find a user by their username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
