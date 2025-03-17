package com.finalproject.demo.repository;

import com.finalproject.demo.entity.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
    LeaveBalance findByUsername(String username);
}
