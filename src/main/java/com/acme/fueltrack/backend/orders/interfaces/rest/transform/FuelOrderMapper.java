package com.acme.fueltrack.backend.orders.interfaces.rest.transform;

import com.acme.fueltrack.backend.orders.application.internal.commands.FuelOrderCommand;
import com.acme.fueltrack.backend.orders.domain.model.valueobjects.FuelType;
import com.acme.fueltrack.backend.orders.interfaces.rest.resources.CreateFuelOrderResource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FuelOrderMapper {

    public FuelOrderCommand toCommand(CreateFuelOrderResource resource) {
        return new FuelOrderCommand(
                UUID.fromString(resource.requesterId()),
                FuelType.valueOf(resource.fuelType().toUpperCase()),
                resource.quantity(),
                resource.note()
        );
    }
}
