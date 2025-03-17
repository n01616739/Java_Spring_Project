package com.finalproject.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")  // Map to the 'employee' table in the database
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role;  // Role can be 'EMPLOYEE' or 'MANAGER'

    private int leaveBalance;  // Remaining leave balance

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeLeave> leaveRequests;  // List of leave requests for the employee

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;  // Associate with the user (for authentication)

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public List<EmployeeLeave> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<EmployeeLeave> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }
}
