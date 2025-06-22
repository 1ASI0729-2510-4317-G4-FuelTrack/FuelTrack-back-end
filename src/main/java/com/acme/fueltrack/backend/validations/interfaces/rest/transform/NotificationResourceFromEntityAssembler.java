package com.acme.fueltrack.backend.validations.interfaces.rest.transform;

import com.acme.fueltrack.backend.validations.domain.model.aggregates.Notification;
import com.acme.fueltrack.backend.validations.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(
                entity.getId(),
                entity.getMessage(),
                entity.isAlreadyRead(),
                entity.getUsersId(),
                entity.getOrdersId()
        );
    }
}
