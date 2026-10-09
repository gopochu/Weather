package com.vadlap.weather.service;

import com.vadlap.weather.common.UserAlreadyExistsException;
import com.vadlap.weather.common.InvalidCredentialsException;
import com.vadlap.weather.entity.Session;
import com.vadlap.weather.entity.User;
import com.vadlap.weather.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegAndAuthService {
    private final SessionService service;
    private final UserRepository repository;

    RegAndAuthService(SessionService service, UserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Transactional
    public UUID register(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("user already exists");
        }

        User savedUser = repository.save(user);
        return service.createSession(savedUser.getId());
    }

    public UUID login(String username, String password) {
        Optional<User> user = repository.findByUsername(username);
        if (user.isEmpty()) {
            throw new InvalidCredentialsException("Incorrect password or user does not exist");
        }

        if (!Objects.equals(password, user.get().getPassword())) {
            throw new InvalidCredentialsException("Incorrect password or user does not exist");
        }

        Session session = service.getSessionByUser(user.get().getId()).orElse(null);
        if (session == null) {
            return service.createSession(user.get().getId());
        }
        return session.getSessionId();
    }
}