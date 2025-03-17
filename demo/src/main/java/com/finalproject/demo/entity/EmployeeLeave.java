package com.finalproject.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_leave") // Renamed to avoid MySQL reserved keyword
public class EmployeeLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;  // The employee who is requesting the leave

    private LocalDate startDate;  // Leave start date
    private LocalDate endDate;    // Leave end date
    private String status;        // Leave status ('PENDING', 'APPROVED', 'REJECTED')
    private String reason;        // Reason for taking the leave

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
