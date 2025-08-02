package com.example.ExpenseTracker_server_JavaSpringboot_qz.service;

import com.example.ExpenseTracker_server_JavaSpringboot_qz.dto.LoginDto;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
