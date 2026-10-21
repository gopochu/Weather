package com.vadlap.weather.controller;

import com.vadlap.weather.entity.dto.RegistrationRequest;
import com.vadlap.weather.service.RegAndAuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/reg")
public class RegistrationController {

    private final RegAndAuthService service;

    RegistrationController(RegAndAuthService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody RegistrationRequest request) {
        UUID sessionId = service.register(request);

        ResponseCookie cookie = ResponseCookie.from("sessionId", sessionId.toString())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }
}
