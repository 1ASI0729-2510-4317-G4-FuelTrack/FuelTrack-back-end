package com.acme.fueltrack.backend.operations.application.internal.commandservice;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Inventory;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateInventoryCommand;
import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateInventoryCommand;
import com.acme.fueltrack.backend.operations.domain.services.InventoryCommandService;
import com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryCommandServiceImpl implements InventoryCommandService {

    private final InventoryRepository repository;

    public InventoryCommandServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Inventory createInventory(CreateInventoryCommand command) {
        Inventory inventory = new Inventory(
                command.fuelType(),
                command.availableQt(),
                command.lastUpdate(),
                command.supplierId()
        );
        return repository.save(inventory);
    }

    @Override
    public Inventory updateInventory(UpdateInventoryCommand command) {
        Inventory inventory = repository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with id: " + command.id()));
        inventory.update(
                command.fuelType(),
                command.availableQt(),
                command.lastUpdate(),
                command.supplierId()
        );
        return repository.save(inventory);
    }
}