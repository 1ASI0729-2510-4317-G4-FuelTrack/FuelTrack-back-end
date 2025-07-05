package com.acme.fueltrack.backend.validations.application.internal.commandservice;

import org.springframework.stereotype.Service;
import com.acme.fueltrack.backend.validations.domain.model.aggregates.Notification;
import com.acme.fueltrack.backend.validations.domain.model.commands.CreateNotificationCommand;
import com.acme.fueltrack.backend.validations.domain.model.commands.MarkNotificationAsReadCommand;
import com.acme.fueltrack.backend.validations.domain.services.NotificationCommandService;
import com.acme.fueltrack.backend.validations.infrastructure.persistence.jpa.repositories.NotificationRepository;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {
        Notification notification = new Notification(command);
        try {
            notificationRepository.save(notification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving notification: " + e.getMessage());
        }
        return Optional.of(notification);
    }

    @Override
    public Optional<Notification> markNotificationAsRead(MarkNotificationAsReadCommand command) {
        return notificationRepository.findById(command.id())
                .map(notification -> {
                    notification.markAsRead(command);
                    return notificationRepository.save(notification);
                });
    }
}
