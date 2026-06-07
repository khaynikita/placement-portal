package com.nikita.placementportal.controller;

import com.nikita.placementportal.entity.Notification;
import com.nikita.placementportal.entity.User;
import com.nikita.placementportal.repository.UserRepository;
import com.nikita.placementportal.security.JwtService;
import com.nikita.placementportal.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @GetMapping
    public List<Notification> getAll(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return notificationService.getAll(user.getId());
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream(@RequestParam String token) {
        String email = jwtService.extractEmail(token);
        User user = userRepository.findByEmail(email).orElseThrow();

        return notificationService.stream(user.getId());
    }

    @PatchMapping("/{id}/read")
    public void markRead(@PathVariable String id) {
        notificationService.markRead(id);
    }
}
