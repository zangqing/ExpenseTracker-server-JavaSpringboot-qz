package com.example.ExpenseTracker_server_JavaSpringboot_qz.service.impl;

import com.example.ExpenseTracker_server_JavaSpringboot_qz.dto.LoginDto;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.dto.RegisterDto;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.entity.Role;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.entity.User;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.exception.ExpenseAPIException;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.repository.RoleRepository;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.repository.UserRepository;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.security.JwtTokenProvider;
import com.example.ExpenseTracker_server_JavaSpringboot_qz.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {

        // Check if username already exists in database
        if (userRepository.existsByUsername(registerDto.getUsername())){
            throw new ExpenseAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // Check if email already exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new ExpenseAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully!";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
}
