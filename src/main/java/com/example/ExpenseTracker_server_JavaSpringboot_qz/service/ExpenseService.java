package com.example.ExpenseTracker_server_JavaSpringboot_qz.service;

import com.example.ExpenseTracker_server_JavaSpringboot_qz.dto.ExpenseDto;

import java.util.List;


public interface ExpenseService {

    ExpenseDto addExpense(ExpenseDto expenseDto);

    ExpenseDto getExpense(Long id);

    List<ExpenseDto> getAllExpenses();

    ExpenseDto editExpense(ExpenseDto expenseDto, Long id);

    void deleteExpense(Long id);
}
