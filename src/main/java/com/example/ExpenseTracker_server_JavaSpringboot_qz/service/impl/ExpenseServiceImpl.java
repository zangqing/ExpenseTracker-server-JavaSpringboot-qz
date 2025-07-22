package com.example.ExpenseTracker_server_JavaSpringboot_qz.service.impl;

import com.example.ExpenseTracker_server_JavaSpringboot_qz.dto.ExpenseDto;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.entity.Expense;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.exception.ResourceNotFoundException;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.repository.ExpenseRepository;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;

    private ModelMapper modelMapper;

    @Override
    public ExpenseDto addExpense(ExpenseDto expenseDto) {

        // convert ExpenseDto into Expense Jpa Entity
        Expense expense = modelMapper.map(expenseDto, Expense.class);

        // Expense Jpa Entity
        Expense savedExpense = expenseRepository.save(expense);

        // Convert saved Expense Jpa Entity object into ExpenseDto object
        ExpenseDto savedExpenseDto = modelMapper.map(savedExpense, ExpenseDto.class);

        return savedExpenseDto;
    }

    @Override
    public ExpenseDto getExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

        return modelMapper.map(expense, ExpenseDto.class);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {

        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream().map((expense) -> modelMapper.map(expense, ExpenseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto editExpense(ExpenseDto expenseDto, Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

        expense.setName(expenseDto.getName());
        expense.setAmount(expenseDto.getAmount());

        Expense editedExpense = expenseRepository.save(expense);

        return modelMapper.map(editedExpense, ExpenseDto.class);
    }

    @Override
    public void deleteExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

        expenseRepository.deleteById(id);
    }
}
