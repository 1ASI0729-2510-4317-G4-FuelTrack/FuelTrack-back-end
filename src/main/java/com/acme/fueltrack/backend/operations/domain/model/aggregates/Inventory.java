package com.acme.fueltrack.backend.operations.domain.model.aggregates;

import com.acme.fueltrack.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "inventories")
@Getter
@NoArgsConstructor
public class Inventory extends AuditableAbstractAggregateRoot<Inventory> {

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


    public void update(String fuelType, BigDecimal availableQt, LocalDateTime lastUpdate, Long supplierId) {
        this.fuelType = fuelType;
        this.availableQt = availableQt;
        this.lastUpdate = lastUpdate;
        this.supplierId = supplierId;
    }
}
