package com.acme.fueltrack.backend.validations.domain.services;

import com.acme.fueltrack.backend.validations.domain.model.aggregates.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {
    List<Notification> getAllNotificationsByUserId(Long userId);
    List<Notification> getUnreadNotificationsByUserId(Long userId);
    Optional<Notification> getNotificationById(Long id);
}
