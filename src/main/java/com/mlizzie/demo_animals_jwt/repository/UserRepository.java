package com.mlizzie.demo_animals_jwt.repository;

import com.mlizzie.demo_animals_jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
    Boolean existsByUsername(String username);
}
