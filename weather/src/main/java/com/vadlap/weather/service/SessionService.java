package com.vadlap.weather.service;

import com.vadlap.weather.entity.Session;
import com.vadlap.weather.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {

    private final SessionRepository repository;

    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

    public UUID createSession(Integer userId) {
        Optional<Session> currentSession = getSessionByUser(userId);

        if (currentSession.isPresent()) {
            return currentSession.get().getSessionId();
        }

        UUID sessionId = UUID.randomUUID();
        Session session = new Session(sessionId, userId, LocalDateTime.now(), LocalDateTime.now().plusHours(24));
        repository.save(session);
        return session.getSessionId();
    }

    public Session getSession(UUID sessionId) {
        Session session = repository.findById(sessionId).orElse(null);

        if (session != null && session.getExpiresAt().isAfter(LocalDateTime.now())) {
            return session;
        } else if (session != null){
            repository.deleteById(session.getSessionId());
        }
        return null;
    }

    public Optional<Session> getSessionByUser(Integer userId) {
        Session session = repository.getByUserId(userId).orElse(null);

        if (session != null && session.getExpiresAt().isAfter(LocalDateTime.now())) {
            return Optional.of(session);
        } else if (session != null) {
            repository.deleteById(session.getSessionId());
        }
        return Optional.empty();
    }

    public void removeSession(UUID sessionId) {
        Optional<Session> session = repository.findById(sessionId);
        if (session.isPresent()) {
            repository.delete(session.get());
        }
    }
}