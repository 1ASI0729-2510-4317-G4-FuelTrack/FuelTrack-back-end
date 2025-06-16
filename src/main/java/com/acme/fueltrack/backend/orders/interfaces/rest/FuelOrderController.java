package com.acme.fueltrack.backend.orders.interfaces.rest;

import com.acme.fueltrack.backend.orders.domain.services.FuelOrderService;
import com.acme.fueltrack.backend.orders.domain.model.aggregates.FuelOrder;
import com.acme.fueltrack.backend.orders.domain.model.commands.FuelOrderCommand;
import com.acme.fueltrack.backend.orders.domain.model.valueobjects.FuelType;
import com.acme.fueltrack.backend.orders.interfaces.rest.resources.CreateFuelOrderResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class FuelOrderController {

    private final FuelOrderService service;

    public FuelOrderController(FuelOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FuelOrder> createOrder(@RequestBody CreateFuelOrderResource resource) {
        FuelOrderCommand command = new FuelOrderCommand(
                UUID.fromString(resource.requesterId()),
                FuelType.valueOf(resource.fuelType()),
                resource.quantity()
        );
        FuelOrder order = service.handle(command);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<FuelOrder>> getOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }
}
