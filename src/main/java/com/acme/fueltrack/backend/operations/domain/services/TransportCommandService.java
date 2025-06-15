package com.acme.fueltrack.backend.operations.domain.services;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Transport;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateTransportCommand;

import java.util.Optional;

public interface TransportCommandService {
    Optional<Transport> handle(CreateTransportCommand command);
}

