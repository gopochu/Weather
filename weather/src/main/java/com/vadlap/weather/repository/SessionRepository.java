package com.vadlap.weather.repository;

import com.vadlap.weather.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {

    Optional<Session> getByUserId(Integer userId);

    void deleteById(UUID sessionId);

    void removeByUserId(UUID userId);
}
