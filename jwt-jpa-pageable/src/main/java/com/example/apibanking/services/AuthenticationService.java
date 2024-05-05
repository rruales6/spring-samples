package com.example.apibanking.services;

import com.example.apibanking.dto.RegisterUserDTO;
import com.example.apibanking.dto.UserLoginDTO;
import com.example.apibanking.entities.User;
import com.example.apibanking.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final  UserService userService;

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public User signup(RegisterUserDTO input) {
        var user = createSecureUser(input);
        return userService.createUser(user);
    }

    public User createSecureUser(RegisterUserDTO input){
        return User.builder()
                .fullName(input.getFullName())
                .identification(input.getIdentification())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();
    }

    public User authenticate(UserLoginDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getIdentification(),
                        input.getPassword()
                )
        );

        return userService.getUser(input.getIdentification()).orElseThrow();
    }

}
