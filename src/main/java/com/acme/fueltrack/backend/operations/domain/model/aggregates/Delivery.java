package com.acme.fueltrack.backend.operations.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delivery_at", nullable = false)
    private LocalDateTime deliveryAt;

    @Column(name = "received_by", nullable = false)
    private String receivedBy;

    @Column(name = "location", nullable = false)
    private String location;

    // Luciana: Temporary relationship with Order - only storing the ID for now
    @Column(name = "orders_id", nullable = true)
    private Long orderId;

    // Luciana: Many-to-one relationship with Transport entity
    @ManyToOne
    @JoinColumn(name = "transports_id", nullable = false)
    private Transport transport;
}
