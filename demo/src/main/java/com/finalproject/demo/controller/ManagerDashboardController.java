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

import java.util.List;

@Controller
public class ManagerDashboardController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private LeaveBalanceService leaveBalanceService;


    // Get the leave history for the employee
    @GetMapping("/manager/leaveHistory")
    public String getLeaveHistory(Model model) {
        String employeeUsername = "employee";  // Replace with actual employee username

        // Get all leave history of the employee
        List<LeaveRequest> allLeaveRequests = leaveRequestService.getLeaveRequestsByEmployee(employeeUsername);
        model.addAttribute("allLeaveRequests", allLeaveRequests);

        return "leaveHistory";  // Leave history page
    }

    // Get the manager dashboard
    @GetMapping("/manager/dashboard")
    public String getManagerDashboard(Model model) {
        String username = "manager";  // Replace with actual session username

// Get all pending leave requests
        List<LeaveRequest> pendingLeaveRequests = leaveRequestService.getPendingLeaveRequests();
        model.addAttribute("pendingLeaveRequests", pendingLeaveRequests);

        return "managerDashboard";  // Return manager dashboard template
    }





    // Approve or reject leave request
    @PostMapping("/manager/approveRejectLeave")
    public String approveOrRejectLeaveRequest(@RequestParam Long leaveRequestId,
                                              @RequestParam String status,
                                              @RequestParam String comment,
                                              Model model) {
        // Update leave request status and add manager's comment
        LeaveRequest leaveRequest = leaveRequestService.approveOrRejectLeaveRequest(leaveRequestId, status, comment);

        // After approving/rejecting, update leave balance for the employee
        leaveBalanceService.updateLeaveBalance(leaveRequest.getUsername(), leaveRequest.getLeaveType(), status);

        // Redirect back to manager dashboard
        return "redirect:/manager/dashboard";
    }
}
