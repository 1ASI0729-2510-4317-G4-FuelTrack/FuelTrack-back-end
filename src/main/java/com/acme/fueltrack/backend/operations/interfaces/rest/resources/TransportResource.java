package com.acme.fueltrack.backend.operations.interfaces.rest.resources;

import lombok.Data;

@Data
public class TransportResource {
    private Long id;
    private String plate;
    private String driver;
    private String tank;
    private boolean available;
}
