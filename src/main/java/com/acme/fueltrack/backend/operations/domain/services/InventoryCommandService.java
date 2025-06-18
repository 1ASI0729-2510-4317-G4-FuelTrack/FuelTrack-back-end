package com.acme.fueltrack.backend.operations.domain.services;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Inventory;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateInventoryCommand;
import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateInventoryCommand;

public interface InventoryCommandService {
    Inventory createInventory(CreateInventoryCommand command);
    Inventory updateInventory(UpdateInventoryCommand command);
}
