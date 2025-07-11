package com.acme.fueltrack.backend.transport.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
@Embeddable
public record Vehicle(
        String plate,
        String brand,
        String model,
        int year,
        int tankCapacity
) {
    public Vehicle {
        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("Plate is required");
        }
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand is required");
        }
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model is required");
        }
        if (year <= 1900) {
            throw new IllegalArgumentException("Year must be valid");
        }
        if (tankCapacity <= 0) {
            throw new IllegalArgumentException("Tank capacity must be greater than 0");
        }
    }

    public String toSummary() {
        return String.format("%s - %s %s (%d) - %d galones",
                plate, brand, model, year, tankCapacity);
    }
}
