package com.acme.fueltrack.backend.validations.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateNotificationResource(
    String message,
    boolean alreadyRead,
    LocalDateTime createdAt,
    int usersId,
    int ordersId
) {}
