package com.example.mailSend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Entity_clas, Long> {
    Entity_clas findByEmail(String email);
}