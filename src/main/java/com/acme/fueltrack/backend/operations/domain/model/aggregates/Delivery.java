package com.acme.fueltrack.backend.operations.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime deliveryAt;
    private String receivedBy;
    private String location;

    private Long orderId;
    private Long transportId;

    public Delivery(LocalDateTime deliveryAt, String receivedBy, String location, Long orderId, Long transportId) {
        this.deliveryAt = deliveryAt;
        this.receivedBy = receivedBy;
        this.location = location;
        this.orderId = orderId;
        this.transportId = transportId;
    }
}
