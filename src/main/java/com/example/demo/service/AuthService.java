package com.example.demo.service;

// create user object, save it to the db, sending activation e-mail

import com.example.demo.controller.dto.RegisterRequest;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    //create user and map data from RegisterRequest-object to User-object
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); //encode pw
        user.setCreated(Instant.now());
        user.setEnabled(false);
    }
}
