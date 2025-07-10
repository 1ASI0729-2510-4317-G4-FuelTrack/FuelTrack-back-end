package com.acme.fueltrack.backend.transport.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Dni (String dni) {

    public Dni(){
        this(null);
    }

    public Dni {
        if (!dni.matches("\\d{8}")){
            throw new IllegalArgumentException("DNI inválido");
        }
    }
}