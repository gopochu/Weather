package com.vadlap.weather.service;

import com.vadlap.weather.entity.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CookieService {

    private final Map<UUID, Session> sessions = new ConcurrentHashMap<>();

    public Session createSession (UUID userId) {
        UUID sessionId = UUID.randomUUID();
        Session session = new Session(sessionId, userId, LocalDateTime.now(), LocalDateTime.now().plusHours(24));
        sessions.put(sessionId, session);
        return session;
    }

    public Session getSession(UUID userId) {
        Session session =  sessions.get(userId);

        if (session != null && session.getExpiresAt().isAfter(LocalDateTime.now())) {
            return session;
        } else if (session != null){
            sessions.remove(session.getSessionId());
        }
        return null;
    }

    // TODO: сделать метод удаления сессии
}