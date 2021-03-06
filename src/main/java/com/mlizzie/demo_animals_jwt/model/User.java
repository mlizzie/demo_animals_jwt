package com.mlizzie.demo_animals_jwt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users",schema = "", catalog = "")
@Data
public class User extends BaseEntity {

    public  User(){}
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Animal> animals;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

}
