package com.mlizzie.demo_animals_jwt.controllers;

import com.mlizzie.demo_animals_jwt.dto.AnimalDto;
import com.mlizzie.demo_animals_jwt.model.Animal;
import com.mlizzie.demo_animals_jwt.model.Gender;
import com.mlizzie.demo_animals_jwt.model.User;
import com.mlizzie.demo_animals_jwt.service.AnimalService;
import com.mlizzie.demo_animals_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import static com.mlizzie.demo_animals_jwt.dto.AnimalDto.mapToAnimalDto;

@RestController
@RequestMapping(value = "/animal/")
public class AnimalRestController {

    private final UserService userService;
    private final AnimalService animalService;

    @Autowired AnimalRestController(AnimalService animalService, UserService userService){
        this.animalService = animalService;
        this.userService = userService;
    }

    @GetMapping(value = "all")
    public List<AnimalDto> getAnimalAll(){
        return mapToAnimalDto(getUser().getAnimals());
    }

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody AnimalDto requestDto)  {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Animal animal = null;
        try {
            animal = new Animal(requestDto.getNickname(),
                    formatter.parse(requestDto.getBirthDate()),
                    Gender.valueOf(requestDto.getGender()),
                    getUser());
            animalService.createAnimal(animal);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity("\"Invalid date\"", HttpStatus.valueOf(402));
        }catch (Exception e){
            return new ResponseEntity("\"Invalid date\"", HttpStatus.valueOf(402));
        }

        return ResponseEntity.ok("");
    }

    @PostMapping(value = "update")
    public ResponseEntity update(@RequestBody AnimalDto requestDto)  {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Animal animal = null;
        try {
            animal = new Animal(requestDto.getNickname(),
                    formatter.parse(requestDto.getBirthDate()),
                    Gender.valueOf(requestDto.getGender()),
                    getUser());
            animalService.updateAnimal(animal);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity("\"Invalid date\"", HttpStatus.valueOf(402));
        }catch (Exception e){
            return new ResponseEntity("\"Invalid date\"", HttpStatus.valueOf(402));
        }

        return ResponseEntity.ok("");
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        Animal animal = animalService.getAnimal(id,getUser().getId());
        if (animal != null) {
            animalService.deleteAnimal(id);
        }
    }
    @GetMapping("/{id}")
    public List<AnimalDto> getAnimal(@PathVariable Long id){
        Animal animal = animalService.getAnimal(id,getUser().getId());
        if (animal == null) return null;
        return mapToAnimalDto(List.of(animal));
    }

    private User getUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userService.findByUsername(userDetails.getUsername());
    }
}
