package com.acme.fueltrack.backend.operations.interfaces.rest.transform;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Inventory;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.InventoryResource;

public class InventoryResourceFromEntityAssembler {
    public static InventoryResource toResourceFromEntity(Inventory inventory) {
        return new InventoryResource(
                inventory.getId(),
                inventory.getFuelType(),
                inventory.getAvailableQt(),
                inventory.getLastUpdate(),
                inventory.getSupplierId()
        );
    }
}
