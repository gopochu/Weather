package com.vadlap.weather.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Session {
    private UUID sessionId;
    private UUID userId;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
