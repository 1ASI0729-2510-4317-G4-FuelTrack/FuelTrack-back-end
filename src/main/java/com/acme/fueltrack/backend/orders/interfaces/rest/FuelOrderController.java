package com.acme.fueltrack.backend.orders.interfaces.rest;

import com.acme.fueltrack.backend.orders.application.services.FuelOrderService;
import com.acme.fueltrack.backend.orders.domain.model.aggregates.FuelOrder;
import com.acme.fueltrack.backend.orders.interfaces.rest.resources.CreateFuelOrderResource;
import com.acme.fueltrack.backend.orders.interfaces.rest.transform.FuelOrderMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class FuelOrderController {

    private final FuelOrderService service;
    private final FuelOrderMapper mapper;

    public FuelOrderController(FuelOrderService service, FuelOrderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<FuelOrder> createOrder(@RequestBody CreateFuelOrderResource resource) {
        FuelOrder order = service.createOrder(
                mapper.toCommand(resource).requesterId(),
                mapper.toCommand(resource).fuelType(),
                mapper.toCommand(resource).quantity()
        );
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<FuelOrder>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }
}
