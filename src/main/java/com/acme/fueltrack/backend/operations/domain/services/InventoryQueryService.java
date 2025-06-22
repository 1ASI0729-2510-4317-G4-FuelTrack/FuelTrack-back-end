package com.acme.fueltrack.backend.operations.domain.services;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryQueryService {

    List<Inventory> handleGetAllInventories();

    Optional<Inventory> handleGetInventoryById(Long id);

    List<Inventory> handleGetInventoriesByFuelType(String fuelType);

    List<Inventory> handleGetInventoriesBySupplierId(Long supplierId);
}
