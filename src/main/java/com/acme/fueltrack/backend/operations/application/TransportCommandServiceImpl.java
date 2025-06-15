package com.acme.fueltrack.backend.operations.application;

import org.springframework.stereotype.Service;
import com.acme.fueltrack.backend.operations.domain.model.aggregates.Transport;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateTransportCommand;
import com.acme.fueltrack.backend.operations.domain.services.TransportCommandService;
import com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories.TransportRepository;

import java.util.Optional;

@Service
public class TransportCommandServiceImpl implements TransportCommandService {

    private final TransportRepository transportRepository;

    public TransportCommandServiceImpl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    public Optional<Transport> handle(CreateTransportCommand command) {
        Transport transport = new Transport(command);
        try {
            transportRepository.save(transport);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving transport: " + e.getMessage());
        }
        return Optional.of(transport);
    }
}