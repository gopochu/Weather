package com.vadlap.weather.controller;

import com.vadlap.weather.entity.dto.LocationsRequest;
import com.vadlap.weather.entity.dto.LocationsResponse;
import com.vadlap.weather.service.LocationsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
public class LocationsController {
    private final LocationsService service;

    @PostMapping
    public ResponseEntity<LocationsResponse> addLocation(@Valid @RequestBody LocationsRequest request) throws IOException, InterruptedException {
        return service.addLocation(request.sessionId(), request.latitude(), request.longitude());
    }
}
