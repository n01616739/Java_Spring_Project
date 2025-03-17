package com.finalproject.demo.controller;

import com.finalproject.demo.entity.LeaveRequest;
import com.finalproject.demo.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/leaveRequests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    // API to submit leave request
    @PostMapping("/submit")
    public LeaveRequest submitLeaveRequest(@RequestParam String username,
                                           @RequestParam String leaveType,
                                           @RequestParam int duration,
                                           @RequestParam String reason,
                                           @RequestParam String startDate,
                                           @RequestParam String endDate) {
        try {
            // Parse the date strings to LocalDate
            LocalDate start = LocalDate.parse(startDate);  // Parse start date
            LocalDate end = LocalDate.parse(endDate);  // Parse end date

            // Call the service method to submit the leave request
            return leaveRequestService.submitLeaveRequest(username, leaveType, duration, reason, start, end);
        } catch (DateTimeParseException e) {
            // Handle the case where date parsing fails
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd' format.", e);
        }
    }

    // API to approve/reject leave request
    @PostMapping("/approveReject")
    public LeaveRequest approveOrRejectLeaveRequest(@RequestParam Long id,
                                                    @RequestParam String status,
                                                    @RequestParam String comment) {
        return leaveRequestService.approveOrRejectLeaveRequest(id, status, comment);
    }
}
