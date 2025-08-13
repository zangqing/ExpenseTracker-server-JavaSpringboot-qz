package com.example.ExpenseTracker_server_JavaSpringboot_qz.repository;

import com.example.ExpenseTracker_server_JavaSpringboot_qz.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
