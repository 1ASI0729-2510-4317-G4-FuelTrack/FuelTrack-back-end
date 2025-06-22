package com.acme.fueltrack.backend.validations.domain.services;

import com.acme.fueltrack.backend.validations.domain.model.aggregates.Notification;
import com.acme.fueltrack.backend.validations.domain.model.commands.CreateNotificationCommand;
import com.acme.fueltrack.backend.validations.domain.model.commands.MarkNotificationAsReadCommand;

import java.util.Optional;

public interface NotificationCommandService {
    Optional<Notification> handle(CreateNotificationCommand command);
    Optional<Notification> markNotificationAsRead(MarkNotificationAsReadCommand command);
}
