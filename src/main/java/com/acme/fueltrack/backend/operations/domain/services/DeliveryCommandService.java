package com.acme.fueltrack.backend.operations.domain.services;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Delivery;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateDeliveryCommand;
import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateDeliveryCommand;

import java.util.Optional;

public interface DeliveryCommandService {
    Optional<Delivery> handle(CreateDeliveryCommand command);
    Optional<Delivery> updateDelivery(UpdateDeliveryCommand command);
}
