package com.vadlap.weather.service;

import com.vadlap.weather.common.UserAlreadyExistsException;
import com.vadlap.weather.common.InvalidCredentialsException;
import com.vadlap.weather.entity.Session;
import com.vadlap.weather.entity.User;
import com.vadlap.weather.entity.dto.RegistrationRequest;
import com.vadlap.weather.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegAndAuthService {
    private final SessionService service;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    RegAndAuthService(SessionService service, UserRepository repository, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UUID register(RegistrationRequest request) {
        User user = new User(request.username(), request.password());
        if (repository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("user already exists");
        }

        User newUser = new User(
            user.getUsername(),
            passwordEncoder.encode(user.getPassword())
        );

        User savedUser = repository.save(newUser);
        return service.createSession(savedUser.getId());
    }

    public UUID login(String username, String password) {
        Optional<User> user = repository.findByUsername(username);
        if (user.isEmpty()) {
            throw new InvalidCredentialsException("Incorrect password or user does not exist");
        }

        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new InvalidCredentialsException("Incorrect password or user does not exist");
        }

        Session session = service.getSessionByUser(user.get().getId()).orElse(null);
        if (session == null) {
            return service.createSession(user.get().getId());
        }
        return session.getSessionId();
    }
}