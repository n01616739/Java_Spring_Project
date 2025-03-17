package com.finalproject.demo.controller;

import com.finalproject.demo.entity.LeaveRequest;
import com.finalproject.demo.entity.LeaveBalance;
import com.finalproject.demo.service.LeaveRequestService;
import com.finalproject.demo.service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EmployeeDashboardController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private LeaveBalanceService leaveBalanceService;

    // Get the employee dashboard
    @GetMapping("/employee/dashboard")
    public String getEmployeeDashboard(Model model) {
        String username = "employee";  // Replace with actual session username

        // Get leave balance for the employee
        LeaveBalance leaveBalance = leaveBalanceService.getLeaveBalance(username);


        if (leaveBalance == null) {
            model.addAttribute("error", "No leave balance found for the employee.");
            return "employeeDashboard";  // Show error if leave balance is not found
        }

        model.addAttribute("leaveBalance", leaveBalance);

        // Get the past leave requests for the employee
        List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveRequestsByEmployee(username);
        model.addAttribute("leaveRequests", leaveRequests);

        return "employeeDashboard";
    }

    // Submit a new leave request
    @PostMapping("/employee/submitLeave")
    public String submitLeaveRequest(@RequestParam String leaveType,
                                     @RequestParam int duration,
                                     @RequestParam String reason,
                                     @RequestParam String startDate,
                                     @RequestParam String endDate) {
        String username = "employee";  //
        // Parse dates from string (implement date parsing logic)
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        // Submit leave request to the service
        leaveRequestService.submitLeaveRequest("employee", leaveType, duration, reason, start, end);
        return "redirect:/employee/dashboard";  // Redirect to dashboard after submission
    }
}
