package com.acme.fueltrack.backend.transport.application;

import com.acme.fueltrack.backend.transport.domain.model.aggregates.Driver;
import com.acme.fueltrack.backend.transport.domain.model.commands.CreateDriverCommand;
import com.acme.fueltrack.backend.transport.domain.model.commands.DeleteDriverCommnad;
import com.acme.fueltrack.backend.transport.domain.model.commands.UpdateDriverCommand;
import com.acme.fueltrack.backend.transport.domain.model.valueobjects.Dni;
import com.acme.fueltrack.backend.transport.domain.model.valueobjects.EmailAddress;
import com.acme.fueltrack.backend.transport.domain.services.DriverCommandService;
import com.acme.fueltrack.backend.transport.infrastruture.persistence.jpa.repositories.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverCommandServiceImpl implements DriverCommandService {

    private final DriverRepository driverRepository;

    public DriverCommandServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Optional<Driver> handle(CreateDriverCommand command) {
        var emailAddress = new EmailAddress(command.email());
        var dni = new Dni(command.dni());

        driverRepository.findByEmail(emailAddress).map(driver -> {
            throw new IllegalArgumentException("Driver with email " + emailAddress + " already exists");
        });

        driverRepository.findByDni(dni).ifPresent(driver -> {
            throw new IllegalArgumentException("Driver with DNI " + dni + " already exists");
        });

        var driver = new Driver(command);
        var createdDriver = driverRepository.save(driver);
        return Optional.of(createdDriver);
    }

    @Override
    public Optional<Driver> handle(UpdateDriverCommand command) {
        return Optional.empty();
    }

    @Override
    public Optional<Driver> handle(DeleteDriverCommnad command) {
        var dni = new Dni(command.id());

        return driverRepository.findByDni(dni).map(driver -> {
            driverRepository.delete(driver);
            return driver;
        });
    }



}
