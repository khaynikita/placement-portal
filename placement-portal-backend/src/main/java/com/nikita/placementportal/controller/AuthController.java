package com.nikita.placementportal.controller;

import com.nikita.placementportal.dto.auth.LoginRequestDTO;
import com.nikita.placementportal.dto.auth.RegisterRequestDTO;
import com.nikita.placementportal.entity.User;
import com.nikita.placementportal.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public Map<String, String> registerUser(
            @RequestBody RegisterRequestDTO request
    ) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();

        userRepository.save(user);

        return Map.of(
                "message",
                "User registered successfully"
        );
    }

    @PostMapping("/login")
    public Map<String, String> loginUser(
            @RequestBody LoginRequestDTO request
    ) {

        return Map.of(
                "message",
                "Login successful"
        );
    }

    @GetMapping("/me")
    public Map<String, String> getCurrentUser() {

        return Map.of(
                "name",
                "Nikita"
        );
    }
}