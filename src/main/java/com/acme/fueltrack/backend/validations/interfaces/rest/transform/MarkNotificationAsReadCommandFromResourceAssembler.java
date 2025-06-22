package com.acme.fueltrack.backend.validations.interfaces.rest.transform;

import com.acme.fueltrack.backend.validations.domain.model.commands.MarkNotificationAsReadCommand;
import com.acme.fueltrack.backend.validations.interfaces.rest.resources.MarkNotificationAsReadResource;

public class MarkNotificationAsReadCommandFromResourceAssembler {
    public static MarkNotificationAsReadCommand toCommandFromResource(MarkNotificationAsReadResource resource) {
        return new MarkNotificationAsReadCommand(resource.id());
    }
}
