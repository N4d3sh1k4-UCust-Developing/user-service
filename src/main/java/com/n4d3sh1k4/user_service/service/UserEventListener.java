package com.n4d3sh1k4.user_service.service;

import com.n4d3sh1k4.user_service.config.RabbitConfig;
import com.n4d3sh1k4.user_service.domain.model.UserProfile;
import com.n4d3sh1k4.user_service.dto.UserCreatedEvent;
import com.n4d3sh1k4.user_service.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserEventListener {

    private final UserProfileRepository profileRepository;

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handleUserCreated(UserCreatedEvent event) {
        log.info("Received event for user creation: ID={}, Username={}", event.id(), event.username());

        if (profileRepository.existsById(event.id())) {
            log.warn("Profile already exists for user ID: {}. Skipping.", event.id());
            return;
        }

        UserProfile profile = new UserProfile();
        profile.setId(event.id());
        profile.setUsername(event.username());
        profile.setEmail(event.email());
        // profile.setAvatarUrl("default_avatar.png"); // Можно задать дефолты

        profileRepository.save(profile);
        log.info("Successfully created profile for user: {}", event.username());
    }
}
