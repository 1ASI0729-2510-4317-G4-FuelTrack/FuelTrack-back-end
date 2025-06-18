package com.acme.fueltrack.backend.operations.application;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Inventory;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateInventoryCommand;
import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateInventoryCommand;
import com.acme.fueltrack.backend.operations.domain.services.InventoryCommandService;
import com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryCommandServiceImpl implements InventoryCommandService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory createInventory(CreateInventoryCommand command) {
        Inventory inventory = new Inventory(
                command.fuelType(),
                command.availableQt(),
                command.lastUpdate(),
                command.supplierId()
        );
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(UpdateInventoryCommand command) {
        return inventoryRepository.findById(command.id())
                .map(inventory -> {
                    inventory.setFuelType(command.fuelType());
                    inventory.setAvailableQt(command.availableQt());
                    inventory.setLastUpdate(command.lastUpdate());
                    inventory.setSupplierId(command.supplierId());
                    return inventoryRepository.save(inventory);
                })
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with id: " + command.id()));
    }
}
