package com.acme.fueltrack.backend.operations.interfaces.rest.transform;

import com.acme.fueltrack.backend.operations.interfaces.rest.resources.CreateTransportResource;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateTransportCommand;

public class CreateTransportCommandFromResourceAssembler {

    public static CreateTransportCommand toCommand(CreateTransportResource resource) {
        return new CreateTransportCommand(
                resource.getPlate(),
                resource.getDriver(),
                resource.getTank(),
                resource.isAvailable()
        );
    }
}
