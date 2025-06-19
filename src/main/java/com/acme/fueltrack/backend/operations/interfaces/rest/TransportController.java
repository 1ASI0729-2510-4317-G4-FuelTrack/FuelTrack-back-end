package com.acme.fueltrack.backend.operations.interfaces.rest;

import com.acme.fueltrack.backend.operations.domain.services.TransportCommandService;
import com.acme.fueltrack.backend.operations.domain.services.TransportQueryService;
import com.acme.fueltrack.backend.operations.domain.model.queries.GetAllTransportQuery;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.CreateTransportResource;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.UpdateTransportResource;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.TransportResource;
import com.acme.fueltrack.backend.operations.interfaces.rest.transform.CreateTransportCommandFromResourceAssembler;
import com.acme.fueltrack.backend.operations.interfaces.rest.transform.UpdateTransportCommandFromResourceAssembler;
import com.acme.fueltrack.backend.operations.interfaces.rest.transform.TransportResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transports")
public class TransportController {

    private final TransportQueryService transportQueryService;
    private final TransportCommandService transportCommandService;

    public TransportController(TransportQueryService queryService, TransportCommandService commandService) {
        this.transportQueryService = queryService;
        this.transportCommandService = commandService;
    }

    // GET all transports
    @GetMapping
    public ResponseEntity<List<TransportResource>> getAll() {
        var transports = transportQueryService.handle(new GetAllTransportQuery());
        var resources = transports.stream()
                .map(TransportResourceFromEntityAssembler::toResource)
                .toList();
        return ResponseEntity.ok(resources);
    }

    // POST create transport
    @PostMapping
    public ResponseEntity<TransportResource> create(@RequestBody CreateTransportResource resource) {
        var command = CreateTransportCommandFromResourceAssembler.toCommand(resource);

        var optionalTransport = transportCommandService.handle(command);

        if (optionalTransport.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Puedes usar notFound() si prefieres
        }

        var transport = optionalTransport.get();
        var result = TransportResourceFromEntityAssembler.toResource(transport);
        return ResponseEntity.ok(result);
    }
    // PUT update transport
    @PutMapping("/{id}")
    public ResponseEntity<TransportResource> update(@PathVariable Long id, @RequestBody UpdateTransportResource resource) {
        var command = UpdateTransportCommandFromResourceAssembler.toCommand(id, resource);
        var optionalTransport = transportCommandService.updateTransport(command);

        if (optionalTransport.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var updated = optionalTransport.get();
        var result = TransportResourceFromEntityAssembler.toResource(updated);
        return ResponseEntity.ok(result);
    }



}
