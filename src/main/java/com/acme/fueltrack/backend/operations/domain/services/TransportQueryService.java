package com.acme.fueltrack.backend.operations.domain.services;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Transport;
import com.acme.fueltrack.backend.operations.domain.model.queries.GetAllTransportQuery;
import com.acme.fueltrack.backend.operations.domain.model.queries.GetAvailableTransportQuery;
import com.acme.fueltrack.backend.operations.domain.model.queries.GetTransportByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TransportQueryService {

    Optional<Transport> handle(GetTransportByIdQuery query);

    List<Transport> handle(GetAllTransportQuery query);

    List<Transport> handle(GetAvailableTransportQuery query);
}

