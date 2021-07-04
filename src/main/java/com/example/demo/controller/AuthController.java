package com.example.demo.controller;

import com.example.demo.controller.dto.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //transfer user details like name, pw, email to the service
    @PostMapping("/signup")
    public void signup(@RequestBody RegisterRequest registerRequest){

    }

}

