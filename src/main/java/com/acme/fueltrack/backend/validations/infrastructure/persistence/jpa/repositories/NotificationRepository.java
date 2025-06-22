package com.acme.fueltrack.backend.validations.infrastructure.persistence.jpa.repositories;

import com.acme.fueltrack.backend.validations.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserId(Long userId);
    List<Notification> findAllByUserIdAndAlreadyReadFalse(Long userId);
}
