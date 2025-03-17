package com.finalproject.demo.repository;

import com.finalproject.demo.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByUsername(String username);
    List<LeaveRequest> findByApprovalStatus(String status);
}
