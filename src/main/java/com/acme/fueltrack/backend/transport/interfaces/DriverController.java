package com.acme.fueltrack.backend.transport.interfaces;

import com.acme.fueltrack.backend.transport.domain.model.aggregates.Driver;
import com.acme.fueltrack.backend.transport.domain.model.commands.DeleteDriverCommnad;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetDriverByIdQuery;
import com.acme.fueltrack.backend.transport.domain.services.DriverCommandService;
import com.acme.fueltrack.backend.transport.domain.services.DriverQueryService;
import com.acme.fueltrack.backend.transport.interfaces.rest.resource.CreateDriverResource;
import com.acme.fueltrack.backend.transport.interfaces.rest.resource.DriverResource;
import com.acme.fueltrack.backend.transport.interfaces.rest.transform.CreateDriverCommandFromResourceAssembler;
import com.acme.fueltrack.backend.transport.interfaces.rest.transform.DriverResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProfileController class
 * <p>
 *     This class is the entry point for all the REST endpoints related to the Profile entity.
 * </p>
 */
@RestController
@RequestMapping(value="/api/v1/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Drivers", description = "Driver Management Endpoints")
public class DriverController {

    private final DriverQueryService driverQueryService;
    private final DriverCommandService driverCommandService;

    public DriverController(DriverQueryService driverQueryService, DriverCommandService driverCommandService) {
        this.driverQueryService = driverQueryService;
        this.driverCommandService = driverCommandService;
    }

    @PostMapping
    public ResponseEntity<DriverResource> createDriver(@RequestBody CreateDriverResource resource) {
        var createDriverCommand = CreateDriverCommandFromResourceAssembler.toCommandFromResource(resource);
        var driver = driverCommandService.handle(createDriverCommand);

        if(driver.isEmpty()) return ResponseEntity.notFound().build();

        var driverResource = DriverResourceFromEntityAssembler.toResourceFromEntity(driver.get());

        return new ResponseEntity<>(driverResource, HttpStatus.CREATED);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<DriverResource> getDriver(@PathVariable String driverId) {
        var getDriverByIdQuery = new GetDriverByIdQuery(driverId);
        var driver = driverQueryService.handle(getDriverByIdQuery);

        if(driver.isEmpty()) return ResponseEntity.notFound().build();

        var driverResource = DriverResourceFromEntityAssembler.toResourceFromEntity(driver.get());

        return new ResponseEntity<>(driverResource, HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<DriverResource>> getAllDrivers() {
        var drivers = driverQueryService.handle(); // List<Driver>
        var driverResources = drivers.stream()
                .map(DriverResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(driverResources);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> deleteDriver(@PathVariable String dni) {
        var deleteCommand = new DeleteDriverCommnad(dni);
        var result = driverCommandService.handle(deleteCommand);

        if (result.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build(); // 204
    }




}
