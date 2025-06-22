package com.acme.fueltrack.backend.validations.interfaces.rest.transform;

import com.acme.fueltrack.backend.validations.domain.model.commands.CreateNotificationCommand;
import com.acme.fueltrack.backend.validations.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(
            resource.message(),
            resource.alreadyRead(),
            resource.createdAt(),
            resource.usersId(),
            resource.ordersId()
        );
    }
}
