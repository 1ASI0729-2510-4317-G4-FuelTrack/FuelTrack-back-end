package com.acme.fueltrack.backend.transport.domain.model.commands;

public record CreateDriverCommand(
        String firstName, String lastName, String dni, String email, String plate, String brand, String model, int year, int tankCapacity
) {
}
