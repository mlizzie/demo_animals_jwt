package com.mlizzie.demo_animals_jwt.dto;

import com.mlizzie.demo_animals_jwt.model.Animal;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AnimalDto {


    private String nickname;
    private String birthDate;
    private String gender;

    public AnimalDto(String nickname,String birthDate, String gender) {
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public AnimalDto() {
    }

    static public List<AnimalDto> mapToAnimalDto(List<Animal> animals){
        List<AnimalDto> listResponseAnimal = new ArrayList<>();
        for (Animal animal:animals) {
            listResponseAnimal.add(new AnimalDto(animal.getNickname(),
                    animal.getBirthDate().toString(),animal.getGender().name()));
        }
        return listResponseAnimal;
    }
}
