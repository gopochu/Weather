package com.vadlap.weather.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

public record RegistrationRequest(
    @NotBlank
    @Size(min = 4, max = 50)
    String username,
    @NotBlank
    @Size(min = 5, max = 60)
    String password
) {}