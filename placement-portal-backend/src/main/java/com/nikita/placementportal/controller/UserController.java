package com.nikita.placementportal.controller;

import com.nikita.placementportal.dto.user.UserSummaryDTO;
import com.nikita.placementportal.entity.User;
import com.nikita.placementportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<UserSummaryDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserSummaryDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getPhone()
                ))
                .toList();
    }
}
