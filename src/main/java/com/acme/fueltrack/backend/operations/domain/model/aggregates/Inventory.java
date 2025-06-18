package com.acme.fueltrack.backend.operations.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fuelType;

    private BigDecimal availableQt;

    private LocalDateTime lastUpdate;

    private Long supplierId;

    public Inventory(String fuelType, BigDecimal availableQt, LocalDateTime lastUpdate, Long supplierId) {
        this.fuelType = fuelType;
        this.availableQt = availableQt;
        this.lastUpdate = lastUpdate;
        this.supplierId = supplierId;
    }
}
