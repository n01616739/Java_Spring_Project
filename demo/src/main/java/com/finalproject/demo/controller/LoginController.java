package com.finalproject.demo.controller;

import com.finalproject.demo.entity.LeaveRequest;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.service.LeaveRequestService;
import com.finalproject.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LeaveRequestService leaveRequestService;

    // Show the login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Login page
    }

    // Handle login form submission
    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        User user = userService.findUserByUsername(username);

        // If user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            // Fetch the leave requests for the user after login
            model.addAttribute("username", username);
            model.addAttribute("leaveRequests", leaveRequestService.getLeaveRequestsByEmployee(username));

            // Redirect to the appropriate dashboard based on role
            if ("EMPLOYEE".equals(user.getRole())) {
                return "redirect:/employee/dashboard";  // Redirect to employee dashboard
            } else if ("MANAGER".equals(user.getRole())) {
                return "redirect:/manager/dashboard";  // Redirect to manager dashboard
            }
        }

        // If credentials are invalid, show error message
        model.addAttribute("error", "Invalid credentials");
        return "login";  // Return to login page if credentials are invalid
    }

    // Handle logout (invalidate session)
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";  // Redirect to login page after logout
    }
}
