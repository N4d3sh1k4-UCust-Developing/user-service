package com.n4d3sh1k4.user_service.dto;

import java.util.UUID;

public record UserCreatedEvent(UUID id, String username, String email) {}
