package com.vadlap.weather.service;

import com.vadlap.weather.client.OpenWeatherClient;
import com.vadlap.weather.common.InvalidCredentialsException;
import com.vadlap.weather.entity.Locations;
import com.vadlap.weather.entity.Session;
import com.vadlap.weather.entity.User;
import com.vadlap.weather.entity.dto.LocationsResponse;
import com.vadlap.weather.entity.dto.WeatherResponse;
import com.vadlap.weather.repository.LocationsRepository;
import com.vadlap.weather.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LocationsService {
    private final LocationsRepository locationsRepository;
    private final SessionRepository sessionRepository;
    private final OpenWeatherClient client;

    public LocationsResponse addLocation(UUID sessionId, double latitude, double longitude) throws IOException, InterruptedException {
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new InvalidCredentialsException("Incorrect latitude or longitude");
        }

        WeatherResponse weatherResponse = client.getWeather(latitude, longitude);

        Optional<Session> session = sessionRepository.findById(sessionId);

        if (session.isPresent()) {
            Locations location = new Locations(
                    weatherResponse.name(),
                    session.get().getUserId(),
                    latitude,
                    longitude
            );
        }



        return new LocationsResponse(weatherResponse.name(), weatherResponse.main().temp());
    }
}
