package com.finalproject.demo.service;

import com.finalproject.demo.entity.LeaveRequest;
import com.finalproject.demo.repository.LeaveRequestRepository;
import com.finalproject.demo.repository.LeaveBalanceRepository;
import com.finalproject.demo.entity.LeaveBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;


    // Method to get leave requests by employee username



    // Get all pending leave requests
    public List<LeaveRequest> getPendingLeaveRequests() {
        return leaveRequestRepository.findByApprovalStatus("PENDING");
    }

    //Submit a new leave request
    public LeaveRequest submitLeaveRequest(String username, String leaveType, int duration, String reason, LocalDate startDate, LocalDate endDate) {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setUsername(username);
        leaveRequest.setLeaveType(leaveType);
        leaveRequest.setDuration(duration);
        leaveRequest.setReason(reason);
        leaveRequest.setStartDate(startDate);
        leaveRequest.setEndDate(endDate);
        leaveRequest.setApprovalStatus("PENDING");
        leaveRequest.setManagerComment("");  // Initially, no comment from manager


        // Save the leave request to the database
        return leaveRequestRepository.save(leaveRequest);
    }


    // Approve or reject leave request
    public LeaveRequest approveOrRejectLeaveRequest(Long id, String status, String managerComment) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElseThrow();
        leaveRequest.setApprovalStatus(status);
        leaveRequest.setManagerComment(managerComment);

        // If the leave request is approved, update the leave balance
        if (status.equals("APPROVED")) {
            LeaveBalance leaveBalance = leaveBalanceRepository.findByUsername(leaveRequest.getUsername());
            if (leaveBalance != null) {
                leaveBalance.setRemainingLeaveDays(leaveBalance.getRemainingLeaveDays() - leaveRequest.getDuration());
                leaveBalanceRepository.save(leaveBalance);
            }
        }

        return leaveRequestRepository.save(leaveRequest);
    }

    // Get leave requests by employee username
    public List<LeaveRequest> getLeaveRequestsByEmployee(String username) {
        return leaveRequestRepository.findByUsername(username);
    }
}
