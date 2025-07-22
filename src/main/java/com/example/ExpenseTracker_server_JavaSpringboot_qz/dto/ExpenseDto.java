package com.example.ExpenseTracker_server_JavaSpringboot_qz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {

    private Long id;
    private String name;
    private double amount;
}
