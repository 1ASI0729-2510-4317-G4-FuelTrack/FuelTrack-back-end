package com.acme.fueltrack.backend.operations.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeliveryResource {
    private Long id;
    private LocalDateTime deliveryAt;
    private String receivedBy;
    private String location;
    private Long orderId;
    private Long transportId;
}
