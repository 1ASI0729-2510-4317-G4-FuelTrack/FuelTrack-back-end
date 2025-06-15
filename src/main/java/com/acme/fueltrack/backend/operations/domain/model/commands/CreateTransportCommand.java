package com.acme.fueltrack.backend.operations.domain.model.commands;

public record CreateTransportCommand(
        String plate,
        String driver,
        String tank,
        boolean available
) {}
