package com.mlizzie.demo_animals_jwt.service.impl;

import com.mlizzie.demo_animals_jwt.model.Animal;
import com.mlizzie.demo_animals_jwt.model.User;
import com.mlizzie.demo_animals_jwt.repository.AnimalRepository;
import com.mlizzie.demo_animals_jwt.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }
    public List<Animal> getAnimals(User user){
        return user.getAnimals();
    }

    public void createAnimal(Animal animal){
        animalRepository.save(animal);
    }

    @Override
    public void updateAnimal(Animal updateAnimal) {
       Animal animal = animalRepository.findByNickname(updateAnimal.getNickname()).orElse(null);
       if (animal == null) return;
       animal.setBirthDate(updateAnimal.getBirthDate());
       animal.setGender(updateAnimal.getGender());
       animal.setUpdated(new Date());
       animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    @Override
    public Animal getAnimal(Long id_animal, Long user_id) {
        return animalRepository.findByIdAndUser_id(id_animal,user_id).orElse(null);
    }
}
