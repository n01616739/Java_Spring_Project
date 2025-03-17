package com.finalproject.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LeaveBalance {

    @Id
    private String username;
    private int totalLeaveDays;
    private int usedLeaveDays;
    private int remainingLeaveDays;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalLeaveDays() {
        return totalLeaveDays;
    }

    public void setTotalLeaveDays(int totalLeaveDays) {
        this.totalLeaveDays = totalLeaveDays;
    }

    public int getUsedLeaveDays() {
        return usedLeaveDays;
    }

    public void setUsedLeaveDays(int usedLeaveDays) {
        this.usedLeaveDays = usedLeaveDays;
    }

    public int getRemainingLeaveDays() {
        return remainingLeaveDays;
    }

    public void setRemainingLeaveDays(int remainingLeaveDays) {
        this.remainingLeaveDays = remainingLeaveDays;
    }

    // Method to calculate remaining leave days
    public void calculateRemainingLeaveDays() {
        this.remainingLeaveDays = totalLeaveDays - usedLeaveDays;
    }
}
