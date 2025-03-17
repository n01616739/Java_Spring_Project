package com.finalproject.demo.controller;

import com.finalproject.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    // Show the user dashboard based on the role (EMPLOYEE or MANAGER)
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            if (user.getRole().equals("EMPLOYEE")) {
                return "employeeDashboard";  // Redirect to Employee Dashboard
            } else if (user.getRole().equals("MANAGER")) {
                return "managerDashboard";  // Redirect to Manager Dashboard
            }
        }

        return "redirect:/login";  // Redirect to login if user is not logged in
    }
}
