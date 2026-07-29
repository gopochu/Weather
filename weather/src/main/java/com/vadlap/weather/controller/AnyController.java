package com.vadlap.weather.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;

@RestController
public class AnyController {
    @GetMapping("/any")
    public ResponseEntity<String> any(@CookieValue(name = "sessionId", required = false) String sessionId) {
        if (sessionId == null) {
            return ResponseEntity.status(401).body("Отсутствует куки");
        }
        return ResponseEntity.ok("sessionId" + sessionId);
    }

    @PostMapping("/session")
    public ResponseEntity<String> makeSession() {
        String session = UUID.randomUUID().toString();

        ResponseCookie cookie = ResponseCookie.from("sessionId", session)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .maxAge(Duration.ofHours(1))
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(cookie.toString());
    }
}
