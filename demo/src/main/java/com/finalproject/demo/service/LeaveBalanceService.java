package com.finalproject.demo.service;

import com.finalproject.demo.entity.LeaveBalance;
import com.finalproject.demo.repository.LeaveBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveBalanceService {

    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;

    // Get the leave balance for a specific employee
    public LeaveBalance getLeaveBalance(String username) {
        LeaveBalance leaveBalance = leaveBalanceRepository.findByUsername(username);
        if (leaveBalance == null) {
            leaveBalance = new LeaveBalance();
            leaveBalance.setUsername(username);
            leaveBalance.setTotalLeaveDays(30); // Set default total leave days
            leaveBalance.setRemainingLeaveDays(30); // Set default remaining leave days
            leaveBalanceRepository.save(leaveBalance);
        }
        return leaveBalance;
    }
    // Update leave balance after approval
    public void updateLeaveBalance(String username, String leaveType, String status) {
        LeaveBalance leaveBalance = leaveBalanceRepository.findByUsername(username);
        if (leaveBalance != null) {
            // Example logic: deduct leave days on approval
            if ("APPROVED".equals(status)) {
                if (leaveType.equals("SICK")) {
                    leaveBalance.setUsedLeaveDays(leaveBalance.getUsedLeaveDays() + 2);  // Deduct 2 days for sick leave
                } else if (leaveType.equals("VACATION")) {
                    leaveBalance.setUsedLeaveDays(leaveBalance.getUsedLeaveDays() + 5);  // Deduct 5 days for vacation
                }
            }
            leaveBalance.calculateRemainingLeaveDays();
            leaveBalanceRepository.save(leaveBalance);
        }
    }
}
