package com.n4d3sh1k4.user_service.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[а-яА-ЯёЁ]+(-[а-яА-ЯёЁ]+)?$",
             message = "The name must be in Cyrillic and may contain a hyphen.")
    String firstName,

    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[а-яА-ЯёЁ]+(-[а-яА-ЯёЁ]+)?$",
             message = "The surname must be in Cyrillic and may contain a hyphen.")
    String lastName,

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone format.")
    String phone,

    @Size(min = 2, max = 50)
    String position
) {}
