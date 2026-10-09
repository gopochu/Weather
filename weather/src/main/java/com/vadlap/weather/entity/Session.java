package com.vadlap.weather.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
//    @GeneratedValue
    @Id
    private UUID sessionId;
    private UUID userId;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
