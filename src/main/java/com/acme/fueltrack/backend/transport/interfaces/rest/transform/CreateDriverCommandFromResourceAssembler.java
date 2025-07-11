package com.acme.fueltrack.backend.transport.interfaces.rest.transform;

import com.acme.fueltrack.backend.transport.domain.model.commands.CreateDriverCommand;
import com.acme.fueltrack.backend.transport.interfaces.rest.resource.CreateDriverResource;

public class CreateDriverCommandFromResourceAssembler {

    public static CreateDriverCommand toCommandFromResource(CreateDriverResource resource) {
        return new CreateDriverCommand(
                resource.firstName(),
                resource.lastName(),
                resource.dni(),
                resource.email(),
                resource.plate(),
                resource.brand(),
                resource.model(),
                resource.year(),
                resource.tankCapacity()
        );
    }
}
