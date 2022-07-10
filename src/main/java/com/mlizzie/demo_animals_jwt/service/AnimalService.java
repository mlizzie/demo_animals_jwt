package com.mlizzie.demo_animals_jwt.service;

import com.mlizzie.demo_animals_jwt.model.Animal;
import com.mlizzie.demo_animals_jwt.model.User;

import java.util.List;

public interface AnimalService {
     List<Animal> getAnimals(User user);
     void createAnimal(Animal animal);
     void updateAnimal(Animal updateAnimal);
     void deleteAnimal(Long id);
     Animal getAnimal(Long id_animal, Long user_id);
}
