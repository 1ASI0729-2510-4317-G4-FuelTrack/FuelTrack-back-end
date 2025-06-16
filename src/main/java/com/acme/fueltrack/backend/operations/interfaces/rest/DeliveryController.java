package com.acme.fueltrack.backend.operations.interfaces.rest;

import com.acme.fueltrack.backend.operations.domain.model.commands.CreateDeliveryCommand;
import com.acme.fueltrack.backend.operations.domain.services.DeliveryCommandService;
import com.acme.fueltrack.backend.operations.domain.services.DeliveryQueryService;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.CreateDeliveryResource;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.DeliveryResource;
import com.acme.fueltrack.backend.operations.interfaces.rest.transform.CreateDeliveryCommandFromResourceAssembler;
import com.acme.fueltrack.backend.operations.interfaces.rest.transform.DeliveryResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    private final DeliveryCommandService deliveryCommandService;
    private final DeliveryQueryService deliveryQueryService;

    public DeliveryController(DeliveryCommandService deliveryCommandService, DeliveryQueryService deliveryQueryService) {
        this.deliveryCommandService = deliveryCommandService;
        this.deliveryQueryService = deliveryQueryService;
    }

    @PostMapping
    public ResponseEntity<DeliveryResource> createDelivery(@RequestBody CreateDeliveryResource resource) {
        CreateDeliveryCommand command = CreateDeliveryCommandFromResourceAssembler.toCommand(resource);
        var delivery = deliveryCommandService.handle(command);
        var deliveryResource = DeliveryResourceFromEntityAssembler.toResource(delivery);
        return ResponseEntity.ok(deliveryResource);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryResource>> getAllDeliveries() {
        var deliveries = deliveryQueryService.handleGetAllDeliveries();
        var resources = deliveries.stream()
                .map(DeliveryResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResource> getDeliveryById(@PathVariable Long id) {
        var delivery = deliveryQueryService.handleGetDeliveryById(id);
        return delivery.map(value ->
                ResponseEntity.ok(DeliveryResourceFromEntityAssembler.toResource(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/transport/{transportId}")
    public ResponseEntity<List<DeliveryResource>> getDeliveriesByTransportId(@PathVariable Long transportId) {
        var deliveries = deliveryQueryService.handleGetDeliveriesByTransportId(transportId);
        var resources = deliveries.stream()
                .map(DeliveryResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<DeliveryResource>> getDeliveriesByOrderId(@PathVariable Long orderId) {
        var deliveries = deliveryQueryService.handleGetDeliveriesByOrderId(orderId);
        var resources = deliveries.stream()
                .map(DeliveryResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
