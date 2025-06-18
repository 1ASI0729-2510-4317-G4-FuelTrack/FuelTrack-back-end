package com.acme.fueltrack.backend.operations.interfaces.rest.transform;

import com.acme.fueltrack.backend.operations.domain.model.commands.CreateInventoryCommand;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.CreateInventoryResource;

public class CreateInventoryCommandFromResourceAssembler {
    public static CreateInventoryCommand toCommandFromResource(CreateInventoryResource resource) {
        return new CreateInventoryCommand(
                resource.fuelType(),
                resource.availableQt(),
                resource.lastUpdate(),
                resource.supplierId()
        );
    }
}
