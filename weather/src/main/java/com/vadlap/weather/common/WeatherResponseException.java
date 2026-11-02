package com.vadlap.weather.common;

import java.io.IOException;

public class WeatherResponseException extends IOException {
    public WeatherResponseException(String message) {
        super(message);
    }
}
