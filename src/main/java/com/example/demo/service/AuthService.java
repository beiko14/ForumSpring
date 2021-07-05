package com.example.demo.service;

// create user object, save it to the db, sending activation e-mail

import com.example.demo.controller.dto.RegisterRequest;
import com.example.demo.model.NotificationEmail;
import com.example.demo.model.User;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    //create user and map data from RegisterRequest-object to User-object
    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); //encode pw
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepository.save(user);

        String randomToken = generateVerificationToken(user);
        // subject, receiver, email-body
        mailService.sendMail(new NotificationEmail("Activate your account",
                user.getEmail(), "Please click on following link to activate your account: " +
                "localhost:8080/api/auth/accountVerification" + randomToken));
    }

    private String generateVerificationToken(User user) {
        String randomToken = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(randomToken);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return randomToken;
    }
}
