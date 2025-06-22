package com.acme.fueltrack.backend.operations.application.internal.queryservice;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Inventory;
import com.acme.fueltrack.backend.operations.domain.services.InventoryQueryService;
import com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryQueryServiceImpl implements InventoryQueryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryQueryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> handleGetAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> handleGetInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public List<Inventory> handleGetInventoriesByFuelType(String fuelType) {
        return inventoryRepository.findByFuelType(fuelType);
    }

    @Override
    public List<Inventory> handleGetInventoriesBySupplierId(Long supplierId) {
        return inventoryRepository.findBySupplierId(supplierId);
    }
}
