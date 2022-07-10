package com.mlizzie.demo_animals_jwt.repository;


import com.mlizzie.demo_animals_jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
