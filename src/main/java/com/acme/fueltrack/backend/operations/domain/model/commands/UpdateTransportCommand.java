package com.acme.fueltrack.backend.operations.domain.model.commands;

public record UpdateTransportCommand(
        Long id,
        String plate,
        String driver,
        String tank,
        boolean available
) {}
