package com.acme.fueltrack.backend.validations.interfaces.rest.resources;

public record NotificationResource(
    Long id,
    String message,
    boolean alreadyRead,
    int userId,
    int orderId
) {}
