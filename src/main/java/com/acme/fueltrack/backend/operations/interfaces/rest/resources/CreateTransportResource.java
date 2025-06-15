package com.acme.fueltrack.backend.operations.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTransportResource {

    @NotBlank(message = "La placa no puede estar vacía")
    private String plate;

    @NotBlank(message = "El nombre del conductor no puede estar vacío")
    private String driver;

    @NotBlank(message = "El tipo de tanque no puede estar vacío")
    private String tank;

    private boolean available;
}
