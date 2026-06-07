package com.nikita.placementportal.service;

import com.nikita.placementportal.entity.Notification;
import com.nikita.placementportal.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public List<Notification> getAll(String userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Notification> getUnread(String userId) {
        return notificationRepository.findByUserIdAndReadFalseOrderByCreatedAtDesc(userId);
    }

    public Notification create(String userId, String message, String type) {
        Notification notification = Notification.builder()
                .userId(userId)
                .message(message)
                .type(type)
                .read(false)
                .createdAt(LocalDateTime.now())
                .build();

        Notification saved = notificationRepository.save(notification);
        send(userId, saved);

        return saved;
    }

    public void markRead(String id) {
        notificationRepository.findById(id).ifPresent(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }

    public SseEmitter stream(String userId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(userId, emitter);
        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(() -> emitters.remove(userId));

        getUnread(userId).forEach(notification -> sendToEmitter(userId, emitter, notification));

        return emitter;
    }

    private void send(String userId, Notification notification) {
        SseEmitter emitter = emitters.get(userId);

        if (emitter != null) {
            sendToEmitter(userId, emitter, notification);
        }
    }

    private void sendToEmitter(String userId, SseEmitter emitter, Notification notification) {
        try {
            emitter.send(SseEmitter.event().name("notification").data(notification));
        } catch (IOException ex) {
            emitters.remove(userId);
        }
    }
}
