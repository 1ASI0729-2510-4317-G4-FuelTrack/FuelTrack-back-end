package com.acme.fueltrack.backend.transport.domain.model.queries;

import com.acme.fueltrack.backend.transport.domain.model.valueobjects.Dni;

public record GetDriverByDniQuery (Dni dni){
}
