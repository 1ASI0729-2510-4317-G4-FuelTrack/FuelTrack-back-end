package com.acme.fueltrack.backend.operations.interfaces.rest;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Inventory;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateInventoryCommand;
import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateInventoryCommand;
import com.acme.fueltrack.backend.operations.domain.services.InventoryCommandService;
import com.acme.fueltrack.backend.operations.domain.services.InventoryQueryService;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.CreateInventoryResource;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.InventoryResource;
import com.acme.fueltrack.backend.operations.interfaces.rest.transform.CreateInventoryCommandFromResourceAssembler;
import com.acme.fueltrack.backend.operations.interfaces.rest.transform.InventoryResourceFromEntityAssembler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private final InventoryCommandService commandService;
    private final InventoryQueryService queryService;

    public InventoryController(InventoryCommandService commandService, InventoryQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<InventoryResource> createInventory(@RequestBody CreateInventoryResource resource) {
        CreateInventoryCommand command = CreateInventoryCommandFromResourceAssembler.toCommandFromResource(resource);
        Inventory inventory = commandService.createInventory(command);
        return ResponseEntity.ok(InventoryResourceFromEntityAssembler.toResourceFromEntity(inventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResource> updateInventory(@PathVariable Long id, @RequestBody CreateInventoryResource resource) {
        UpdateInventoryCommand command = new UpdateInventoryCommand(
                id,
                resource.fuelType(),
                resource.availableQt(),
                resource.lastUpdate(),
                resource.supplierId()
        );
        Inventory inventory = commandService.updateInventory(command);
        return ResponseEntity.ok(InventoryResourceFromEntityAssembler.toResourceFromEntity(inventory));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResource>> getAllInventories() {
        List<Inventory> inventories = queryService.handleGetAllInventories();
        List<InventoryResource> resources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResource> getInventoryById(@PathVariable Long id) {
        return queryService.handleGetInventoryById(id)
                .map(InventoryResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-fuel-type/{fuelType}")
    public ResponseEntity<List<InventoryResource>> getInventoriesByFuelType(@PathVariable String fuelType) {
        List<Inventory> inventories = queryService.handleGetInventoriesByFuelType(fuelType);
        List<InventoryResource> resources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/by-supplier/{supplierId}")
    public ResponseEntity<List<InventoryResource>> getInventoriesBySupplierId(@PathVariable Long supplierId) {
        List<Inventory> inventories = queryService.handleGetInventoriesBySupplierId(supplierId);
        List<InventoryResource> resources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
