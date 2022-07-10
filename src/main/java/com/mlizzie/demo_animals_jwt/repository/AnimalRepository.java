package com.mlizzie.demo_animals_jwt.repository;

import com.mlizzie.demo_animals_jwt.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findByNickname(String name);
    Optional<Animal> findByIdAndUser_id(Long name, Long user_id);
}
