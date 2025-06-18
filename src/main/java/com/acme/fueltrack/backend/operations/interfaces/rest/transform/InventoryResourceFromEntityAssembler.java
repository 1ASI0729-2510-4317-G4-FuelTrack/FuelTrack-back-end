package com.acme.fueltrack.backend.operations.interfaces.rest.transform;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Inventory;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.InventoryResource;

public class InventoryResourceFromEntityAssembler {
    public static InventoryResource toResourceFromEntity(Inventory entity) {
        return new InventoryResource(
                entity.getId(),
                entity.getFuelType(),
                entity.getAvailableQt(),
                entity.getLastUpdate(),
                entity.getSupplierId()
        );
    }
}
