package com.example.ExpenseTracker_server_JavaSpringboot_qz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExpenseAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
