package com.acme.fueltrack.backend.operations.interfaces.rest.transform;

import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateDeliveryCommand;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.UpdateDeliveryResource;

public class UpdateDeliveryCommandFromResourceAssembler {
    public static UpdateDeliveryCommand toCommand(Long id, UpdateDeliveryResource resource) {
        return new UpdateDeliveryCommand(
                id,
                resource.deliveryAt(),
                resource.receivedBy(),
                resource.location(),
                resource.orderId(),
                resource.transportId()
        );
    }
}
