package com.example.ExpenseTracker_server_JavaSpringboot_qz.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("qz"));
        System.out.println(passwordEncoder.encode("admin"));
    }
}
