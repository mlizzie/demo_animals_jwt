package com.mlizzie.demo_animals_jwt.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "roles",schema = "", catalog = "")
@Data
public class Role extends BaseEntity {

    public Role(){ }

    public Role(String name){
        this.name = name;
        this.setCreated(new Date());
        this.setUpdated(new Date());
        this.setStatus(Status.ACTIVE);
    }

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id: " + super.getId() + ", " +
                "name: " + name + "}";
    }
}
