package com.mlizzie.demo_animals_jwt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "animals",schema = "", catalog = "")
@Data
public class Animal extends BaseEntity {

    public  Animal(){}
    public Animal(String nickname, Date birthDate, Gender gender, User user) {

        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.setCreated(new Date());
        this.setUpdated(new Date());
        this.setStatus(Status.ACTIVE);
        this.setUser(user);
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = false, nullable = false)
    Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}






