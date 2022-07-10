package com.mlizzie.demo_animals_jwt.service;

import com.mlizzie.demo_animals_jwt.model.User;
import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
