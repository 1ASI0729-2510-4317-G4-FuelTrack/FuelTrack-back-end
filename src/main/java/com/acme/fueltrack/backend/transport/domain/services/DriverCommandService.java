package com.acme.fueltrack.backend.transport.domain.services;

import com.acme.fueltrack.backend.transport.domain.model.aggregates.Driver;
import com.acme.fueltrack.backend.transport.domain.model.commands.CreateDriverCommand;
import com.acme.fueltrack.backend.transport.domain.model.commands.DeleteDriverCommnad;
import com.acme.fueltrack.backend.transport.domain.model.commands.UpdateDriverCommand;

import java.util.Optional;

public interface DriverCommandService {

    Optional<Driver> handle(CreateDriverCommand command);
    Optional<Driver> handle(UpdateDriverCommand command);
    Optional<Driver> handle(DeleteDriverCommnad command);

}
