package com.vadlap.weather.entity.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record LocationsRequest(
        UUID sessionId,
        double latitude,
        double longitude
) {}
