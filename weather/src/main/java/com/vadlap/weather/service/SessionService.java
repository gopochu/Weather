package com.vadlap.weather.service;

import com.vadlap.weather.entity.Session;
import com.vadlap.weather.repository.SessionRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionService {

    private final SessionRepository repository;

    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

    public UUID createSession(UUID userId) {
        Optional<Session> currentSession = getSessionByUser(userId);

        if (currentSession.isPresent()) {
            return currentSession.get().getSessionId();
        }
//        else if (currentSession.isPresent() && !currentSession.get().getExpiresAt().isAfter(LocalDateTime.now())) {
//            repository.delete(currentSession.get());
//        }

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

    public Optional<Session> getSessionByUser(UUID userId) {
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

//    public void removeSession(UUID userId) {
//        Optional<Session> session = repository.getByUserId(userId);
//        if (session.isPresent()) {
//            repository.removeByUserId(userId);
//        }
//    }
}