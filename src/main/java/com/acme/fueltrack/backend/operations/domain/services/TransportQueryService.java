package com.acme.fueltrack.backend.operations.domain.services;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Transport;
import com.acme.fueltrack.backend.operations.domain.model.queries.GetAllTransportsQuery;
import com.acme.fueltrack.backend.operations.domain.model.queries.GetAvailableTransportsQuery;
import com.acme.fueltrack.backend.operations.domain.model.queries.GetTransportByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TransportQueryService {

    Optional<Transport> handle(GetTransportByIdQuery query);

    List<Transport> handle(GetAllTransportsQuery query);

    List<Transport> handle(GetAvailableTransportsQuery query);
}

