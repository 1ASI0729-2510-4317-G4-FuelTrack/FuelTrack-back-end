package com.acme.fueltrack.backend.validations.domain.model.commands;

import java.time.LocalDateTime;

public record CreateNotificationCommand(
        String message,
        boolean alreadyRead,
        LocalDateTime createdAt,
        int usersId,
        int ordersId
) {}
