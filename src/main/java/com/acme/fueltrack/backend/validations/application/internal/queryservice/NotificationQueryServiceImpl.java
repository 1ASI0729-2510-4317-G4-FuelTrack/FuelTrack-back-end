package com.acme.fueltrack.backend.validations.application.internal.queryservice;

import com.acme.fueltrack.backend.validations.domain.model.aggregates.Notification;
import com.acme.fueltrack.backend.validations.domain.services.NotificationQueryService;
import com.acme.fueltrack.backend.validations.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAllNotificationsByUserId(Long userId) {
        return notificationRepository.findAllByUserId(userId);
    }

    @Override
    public List<Notification> getUnreadNotificationsByUserId(Long userId) {
        return notificationRepository.findAllByUserIdAndAlreadyReadFalse(userId);
    }

    @Override
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }
}
