package com.acme.fueltrack.backend.orders.domain.model.commands;

import com.acme.fueltrack.backend.orders.domain.model.valueobjects.FuelType;

import java.util.UUID;

public record FuelOrderCommand(UUID requesterId, FuelType fuelType, double quantity) {

}
