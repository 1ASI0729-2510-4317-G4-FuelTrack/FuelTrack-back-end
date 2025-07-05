package com.acme.fueltrack.backend.operations.domain.services;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryQueryService {

    List<Delivery> handleGetAllDeliveries();

    Optional<Delivery> handleGetDeliveryById(Long id);

    List<Delivery> handleGetDeliveriesByOrderId(Long orderId); // ✅ cambiado a List

    List<Delivery> handleGetDeliveriesByTransportId(Long transportId); // ✅ necesario
}
