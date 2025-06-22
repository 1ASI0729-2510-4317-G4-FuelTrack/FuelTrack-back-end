package com.acme.fueltrack.backend.orders.interfaces.rest.resources;

import java.util.UUID;
import java.time.LocalDateTime;

public record FuelOrderResource(
        UUID orderId,
        UUID requesterId,
        String fuelType,
        double quantity,
        String status,
        LocalDateTime createdAt
) {}
