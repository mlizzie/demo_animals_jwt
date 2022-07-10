package com.mlizzie.demo_animals_jwt.controllers;

import com.mlizzie.demo_animals_jwt.dto.AuthenticationRequestDto;
import com.mlizzie.demo_animals_jwt.model.User;
import com.mlizzie.demo_animals_jwt.security.jwt.JwtTokenProvider;
import com.mlizzie.demo_animals_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity("\"Invalid username or password\"",HttpStatus.valueOf(401));
        }
    }


    @PostMapping("register")
    public ResponseEntity register(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            if (userService.existsByUsername(requestDto.getUsername())){
                return new ResponseEntity("the user exists",HttpStatus.valueOf(400));
            }
            User user =  userService.register(new User(requestDto.getUsername(),requestDto.getPassword()));
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + requestDto.getUsername() + " not found");
            }
            return this.login(requestDto);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

    }

    @GetMapping("checkUsername/{username}")
    public  ResponseEntity checkUsername(@PathVariable String username){
        if (userService.existsByUsername(username)){
            return new ResponseEntity("the user exists",HttpStatus.valueOf(400));
        }
        return ResponseEntity.ok("username free");
    }

}
