package com.example.ExpenseTracker_server_JavaSpringboot_qz.repository;

import com.example.ExpenseTracker_server_JavaSpringboot_qz.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
