package com.acme.fueltrack.backend.operations.interfaces.rest.transform;

import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateTransportCommand;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.UpdateTransportResource;

public class UpdateTransportCommandFromResourceAssembler {
    public static UpdateTransportCommand toCommand(Long id, UpdateTransportResource resource) {
        return new UpdateTransportCommand(
                id,
                resource.plate(),
                resource.driver(),
                resource.tank(),
                resource.available()
        );
    }
}
