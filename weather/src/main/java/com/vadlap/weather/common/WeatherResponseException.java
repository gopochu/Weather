package com.vadlap.weather.common;

public class WeatherResponseException extends RuntimeException {
  public WeatherResponseException(String message) {
    super(message);
  }
}
