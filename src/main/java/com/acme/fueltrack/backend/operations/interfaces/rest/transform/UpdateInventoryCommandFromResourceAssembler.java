package com.acme.fueltrack.backend.operations.interfaces.rest.transform;

import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateInventoryCommand;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.UpdateInventoryResource;

public class UpdateInventoryCommandFromResourceAssembler {
    public static UpdateInventoryCommand toCommandFromResource(Long id, UpdateInventoryResource resource) {
        return new UpdateInventoryCommand(
                id,
                resource.fuelType(),
                resource.availableQt(),
                resource.lastUpdate(),
                resource.supplierId()
        );
    }
}
