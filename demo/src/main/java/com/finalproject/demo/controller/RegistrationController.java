package com.finalproject.demo.controller;

import com.finalproject.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    // Show the registration form
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // Registration page
    }

    // Handle form submission from the registration page
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {
        // Validate role input
        if (!role.equals("EMPLOYEE") && !role.equals("MANAGER")) {
            model.addAttribute("error", "Invalid role provided");
            return "register";  // Return to registration page if role is invalid
        }

        // Register the user and save to the database
        userService.registerUser(username, password, role);

        return "redirect:/login";  // After registration, redirect to login page
    }
}
