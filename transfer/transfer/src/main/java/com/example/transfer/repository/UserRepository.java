package com.example.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transfer.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
