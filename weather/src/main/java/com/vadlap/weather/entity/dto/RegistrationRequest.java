package com.vadlap.weather.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
    @NotBlank
    @Size(min = 4, max = 50)
    String username,
    @NotBlank
    @Size(min = 5, max = 60)
    String password
) {}