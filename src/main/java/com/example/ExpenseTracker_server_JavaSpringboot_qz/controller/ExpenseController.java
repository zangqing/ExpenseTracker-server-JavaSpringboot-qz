package com.example.ExpenseTracker_server_JavaSpringboot_qz.controller;

import com.example.ExpenseTracker_server_JavaSpringboot_qz.dto.ExpenseDto;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/expenses")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService expenseService;

    // Build Add Expense REST API
    @PostMapping
    public ResponseEntity<ExpenseDto> addExpense(@RequestBody ExpenseDto expenseDto){

        ExpenseDto savedExpense = expenseService.addExpense(expenseDto);

        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    // Build Get Expense REST API
    @GetMapping("{id}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable("id") Long expenseId){
        ExpenseDto expense = expenseService.getExpense(expenseId);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    // Build Get All Expenses REST API
    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(){
        List<ExpenseDto> expenses = expenseService.getAllExpenses();
//        return new ResponseEntity<>(expenses, HttpStatus.OK);
        return ResponseEntity.ok(expenses);
    }

    // Build Edit Expense REST API
    @PutMapping("{id}")
    public ResponseEntity<ExpenseDto> editExpense(@RequestBody ExpenseDto expenseDto,
                                                  @PathVariable("id") Long expenseId){
        ExpenseDto editedExpense =  expenseService.editExpense(expenseDto, expenseId);
        return ResponseEntity.ok(editedExpense);
    }

    // Build Delete Expense REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable("id") Long expenseId){
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok("Expense deleted successfully!");
    }
}
